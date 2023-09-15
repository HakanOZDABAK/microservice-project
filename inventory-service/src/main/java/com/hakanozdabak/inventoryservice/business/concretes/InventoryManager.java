package com.hakanozdabak.inventoryservice.business.concretes;

import com.hakanozdabak.inventoryservice.business.responses.InventoryResponse;
import com.hakanozdabak.inventoryservice.business.abstracts.InventoryService;
import com.hakanozdabak.inventoryservice.dataAccess.abstracts.InventoryRepository;
import com.hakanozdabak.inventoryservice.entities.concretes.Inventory;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class InventoryManager implements InventoryService {

    private final InventoryRepository inventoryRepository;
    @Override
    @Transactional(readOnly = true)
    @SneakyThrows /*This is for demo project. Use try-catch at real project*/
    public List<InventoryResponse> isInStock(List<String> skuCode) {
      log.info("Wait Started");
      Thread.sleep(10000);
      log.info("Wait ended");
        return  inventoryRepository.findBySkuCodeIn(skuCode).stream()
              .map(inventory ->
                      InventoryResponse.builder()
                      .skuCode(inventory.getSkuCode())
                      .isInStock(inventory.getQuantity()> 0)
                      .build()

              ).toList();
    }

    @Override
    public List<Inventory> find() {
        return this.inventoryRepository.findAll();
    }
}
