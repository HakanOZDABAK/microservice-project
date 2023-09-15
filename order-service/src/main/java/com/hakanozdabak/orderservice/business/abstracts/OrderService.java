package com.hakanozdabak.orderservice.business.abstracts;

import com.hakanozdabak.orderservice.business.requests.OrderRequest;

public interface OrderService {

    String placeOrder(OrderRequest orderRequest);
}
