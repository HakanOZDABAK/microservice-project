package com.hakanozdabak.productservice.business.abstracts;

import com.hakanozdabak.productservice.business.requests.ProductRequest;
import com.hakanozdabak.productservice.business.responses.ProductResponse;

import java.util.List;

public interface ProductService {

    void add(ProductRequest productRequest);

    List<ProductResponse> getAll();
}
