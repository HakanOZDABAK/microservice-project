package com.hakanozdabak.orderservice.business.requests;

import com.hakanozdabak.orderservice.business.responses.OrderLineItemsResponse;
import com.hakanozdabak.orderservice.entities.concretes.OrderLineItems;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private List<OrderLineItemsResponse> orderLineItemsResponseList;
}
