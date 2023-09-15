package com.hakanozdabak.productservice.webapi.controllers;

import com.hakanozdabak.productservice.business.abstracts.ProductService;
import com.hakanozdabak.productservice.business.requests.ProductRequest;
import com.hakanozdabak.productservice.business.responses.ProductResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@AllArgsConstructor
public class ProductController {

    private ProductService productService;

   @PostMapping("/add")
   @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody ProductRequest productRequest)
   {
       this.productService.add(productRequest);
   }

   @GetMapping("/getall")
   @ResponseStatus(HttpStatus.CREATED)
    public List<ProductResponse> getAll(){
      return this.productService.getAll();

   }



}
