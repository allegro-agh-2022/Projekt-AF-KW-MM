package com.products.products;

import lombok.Data;

@Data
public class ProductDto {
    public String name;
    public double price;
    public int stock;
}