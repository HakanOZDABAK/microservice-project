package com.hakanozdabak.inventoryservice.business.abstracts;

import com.hakanozdabak.inventoryservice.business.responses.InventoryResponse;
import com.hakanozdabak.inventoryservice.entities.concretes.Inventory;

import java.util.List;

public interface InventoryService {

    List<InventoryResponse> isInStock(List<String> skuCode);
    List<Inventory> find();
}
