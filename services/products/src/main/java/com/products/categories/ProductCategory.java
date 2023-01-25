package com.products.categories;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.products.products.Product;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Table(name="product_categories")
@Entity
@Data
@NoArgsConstructor
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Product> products = new ArrayList<>();

    public ProductCategory(AddProductCategoryDto productCategoryDto) {
        this.name = productCategoryDto.getName();
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }
}
