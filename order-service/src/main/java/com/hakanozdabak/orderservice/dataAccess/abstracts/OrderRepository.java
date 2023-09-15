package com.hakanozdabak.orderservice.dataAccess.abstracts;

import com.hakanozdabak.orderservice.entities.concretes.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Integer> {
}
