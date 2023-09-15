package com.hakanozdabak.orderservice.webapi.controllers;

import com.hakanozdabak.orderservice.business.abstracts.OrderService;
import com.hakanozdabak.orderservice.business.requests.OrderRequest;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/v1/order")
@AllArgsConstructor
public class OrderController {
    private OrderService orderService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
   /* @CircuitBreaker(name = "inventory",fallbackMethod = "fallbackMethod")
    @TimeLimiter(name = "inventory")
    @Retry(name = "inventory")*/
    public String placeOrder(@RequestBody OrderRequest orderRequest){
       return this.orderService.placeOrder(orderRequest);

    }

    public CompletableFuture<String> fallbackMethod(OrderRequest orderRequest, RuntimeException runtimeException){
        return CompletableFuture.supplyAsync(()->"Oops! Something went wrong, please order after some time!");
    }
}
