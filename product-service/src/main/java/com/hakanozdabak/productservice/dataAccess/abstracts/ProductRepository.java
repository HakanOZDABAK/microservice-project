package com.hakanozdabak.productservice.dataAccess.abstracts;

import com.hakanozdabak.productservice.entity.concretes.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
}
