package com.hakanozdabak.orderservice.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineItemsResponse {
    private Integer id;
    private String skuCode;
    private float price;
    private Integer quantity;
}
