package com.hakanozdabak.orderservice.business.concretes;

import com.hakanozdabak.orderservice.business.abstracts.OrderService;
import com.hakanozdabak.orderservice.business.requests.OrderRequest;
import com.hakanozdabak.orderservice.business.responses.InventoryResponse;
import com.hakanozdabak.orderservice.core.utilities.mappers.ModelMapperService;
import com.hakanozdabak.orderservice.dataAccess.abstracts.OrderRepository;
import com.hakanozdabak.orderservice.entities.concretes.Order;
import com.hakanozdabak.orderservice.entities.concretes.OrderLineItems;
import com.hakanozdabak.orderservice.event.OrderPlacedEvent;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class OrderManager implements OrderService {
    private OrderRepository orderRepository;
    private ModelMapperService modelMapperService;
    private final WebClient.Builder webClientBuilder;
    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;
    @Override
    public String placeOrder(OrderRequest orderRequest) {
      Order order = new Order();
      order.setOrderNumber(UUID.randomUUID().toString());
      List<OrderLineItems> orderLineItemsList= orderRequest.getOrderLineItemsResponseList().stream()
                .map(orders -> this.modelMapperService.forResponse().map(orders, OrderLineItems.class)).collect(Collectors.toList());
      order.setOrderLineItemsList(orderLineItemsList);

      List<String> skuCodes =  order.getOrderLineItemsList().stream()
             .map(OrderLineItems::getSkuCode)
                .toList();

      InventoryResponse[] inventoryResponsesArray =  webClientBuilder.build().get()
        .uri("http://inventory-service/api/v1/inventory",uriBuilder -> uriBuilder.queryParam("skuCode",skuCodes).build())
              .retrieve()
                .bodyToMono(InventoryResponse[].class)
                 .block();

      boolean allProductIsInStock = Arrays.stream(inventoryResponsesArray)
                .allMatch(InventoryResponse::isInStock);
      if ( allProductIsInStock){
          this.orderRepository.save(order);
          kafkaTemplate.send("notificationTopic", new OrderPlacedEvent(order.getOrderNumber()));
          return "Order Placed Successfully";
      }
      else {
          throw new IllegalArgumentException("Product is not in stock");
      }


    }
}
