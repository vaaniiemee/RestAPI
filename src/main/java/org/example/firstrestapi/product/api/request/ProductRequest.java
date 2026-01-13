package org.example.firstrestapi.product.api.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ProductRequest {

    @NotBlank(message = "Name cannot be empty")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;

    public ProductRequest() {}

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}