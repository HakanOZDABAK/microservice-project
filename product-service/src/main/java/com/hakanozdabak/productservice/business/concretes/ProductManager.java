package com.hakanozdabak.productservice.business.concretes;

import com.hakanozdabak.productservice.business.abstracts.ProductService;
import com.hakanozdabak.productservice.business.requests.ProductRequest;
import com.hakanozdabak.productservice.business.responses.ProductResponse;
import com.hakanozdabak.productservice.core.utilities.mappers.ModelMapperService;
import com.hakanozdabak.productservice.dataAccess.abstracts.ProductRepository;
import com.hakanozdabak.productservice.entity.concretes.Product;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class ProductManager implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapperService modelMapperService;

    @Override
    public void add(ProductRequest productRequest) {
        Product product = this.modelMapperService.forRequest().map(productRequest,Product.class);
        this.productRepository.save(product);
        log.info("Product {} is saved",product.getId());

    }

    @Override
    public List<ProductResponse> getAll() {
        List<Product> products = productRepository.findAll();
        List<ProductResponse> productsResponse= products.stream()
                .map(product -> this.modelMapperService.forResponse().map(product, ProductResponse.class)).collect(Collectors.toList());
        return productsResponse;
    }
}
