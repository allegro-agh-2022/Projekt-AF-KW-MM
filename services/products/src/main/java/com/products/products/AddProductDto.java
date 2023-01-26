package com.products.products;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddProductDto {
    private String name;
    private double price;
    private int stock;
    private Long categoryId;
}