package org.example.firstrestapi.product.service;

import org.example.firstrestapi.product.api.request.ProductRequest;
import org.example.firstrestapi.product.api.response.ProductResponse;
import org.example.firstrestapi.product.domain.Product;
import org.example.firstrestapi.product.exception.ProductNotFoundException;
import org.example.firstrestapi.product.repository.ProductRepository;
import org.example.firstrestapi.product.support.ProductMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;

    public ProductService(ProductRepository repository, ProductMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public ProductResponse create(ProductRequest request) {
        Product product = mapper.toEntity(request);
        return mapper.toResponse(repository.save(product));
    }

    public ProductResponse getById(Long id) {
        return repository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new ProductNotFoundException("Sorry, we couldn't find a product with ID: " + id));
    }

    public List<ProductResponse> getAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    public ProductResponse update(Long id, ProductRequest request) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Sorry, we couldn't find a product with ID: "+ id));
        product.setName(request.getName());
        return mapper.toResponse(repository.save(product));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ProductNotFoundException("Cannot delete. Sorry, we couldn't find a product with ID: " + id);
        }
        repository.deleteById(id);
    }
}