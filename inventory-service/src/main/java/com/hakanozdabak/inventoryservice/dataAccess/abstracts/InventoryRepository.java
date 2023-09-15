package com.hakanozdabak.inventoryservice.dataAccess.abstracts;

import com.hakanozdabak.inventoryservice.entities.concretes.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory,Integer> {

    List<Inventory> findBySkuCodeIn(List<String> skuCode);
}
