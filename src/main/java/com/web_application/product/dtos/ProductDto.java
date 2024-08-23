package com.web_application.product.dtos;

import lombok.Data;

@Data
public class ProductDto {
    private String name;
    private String description;
    private Double price;
}
