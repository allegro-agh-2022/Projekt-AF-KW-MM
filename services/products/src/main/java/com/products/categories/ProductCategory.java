package com.products.categories;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="product_categories")
@Entity
@Data
@NoArgsConstructor
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    public ProductCategory(AddProductCategoryDto productCategoryDto) {
        this.name = productCategoryDto.getName();
    }
}
