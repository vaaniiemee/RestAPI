package org.example.firstrestapi.product.support;

import org.example.firstrestapi.product.api.request.ProductRequest;
import org.example.firstrestapi.product.api.response.ProductResponse;
import org.example.firstrestapi.product.domain.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product toEntity(ProductRequest request) {
        Product product = new Product();
        product.setName(request.getName());
        return product;
    }

    public ProductResponse toResponse(Product product) {
        return new ProductResponse(product.getId(), product.getName());
    }
}