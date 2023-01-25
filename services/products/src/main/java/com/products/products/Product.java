package com.products.products;

import com.products.stores.Store;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.Data;

@Table(name="products")
@Entity
@NoArgsConstructor
@Data
public class Product {
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

    public Product(String name, double price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public Product(AddProductDto addProductDto) {
        this.name = addProductDto.name;
        this.price = addProductDto.price;
        this.stock = addProductDto.stock;
    }

    public void applyUpdate(UpdateProductDto update) {
        this.name = update.name;
        this.price = update.price;
        this.stock = update.stock;
        this.status = update.status;
    }

    public void setStore(Store store) {
        this.store = store;
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

