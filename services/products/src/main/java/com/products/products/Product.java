package com.products.products;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.products.categories.ProductCategory;
import com.products.stores.Store;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Table(name="products")
@Entity
@Data
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    private double price;

    private int stock;

    private String status = "ACTIVE";

    @ManyToOne
    @JoinColumn(name="store_id", nullable=false)
    private Store store;

    @ManyToOne
    @JoinColumn(name="category_id", nullable=false)
    private ProductCategory category;

    @JsonCreator
    public Product() {
        super();
    }

    public Product(String name, double price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public Product(AddProductDto addProductDto) {
        this.name = addProductDto.getName();
        this.price = addProductDto.getPrice();
        this.stock = addProductDto.getStock();
    }

    public void applyUpdate(UpdateProductDto update) {
        this.name = update.getName();
        this.price = update.getPrice();
        this.stock = update.getStock();
        this.status = update.getStatus();
    }

    public void addStock(int amount) {
        this.stock += amount;
    }

    public void activate() {
        this.status = "ACTIVE";
    }

    public void deactivate() {
        this.status = "INACTIVE";
    }
}

