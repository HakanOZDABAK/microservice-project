package com.hakanozdabak.inventoryservice.webapi.controllers;

import com.hakanozdabak.inventoryservice.business.responses.InventoryResponse;
import com.hakanozdabak.inventoryservice.business.abstracts.InventoryService;
import com.hakanozdabak.inventoryservice.entities.concretes.Inventory;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/inventory")
@AllArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam() List<String> skuCode){
        return this.inventoryService.isInStock(skuCode);
    }
    @GetMapping("/gets")
    public List<Inventory> gets(){
        return this.inventoryService.find();
    }
}
