package com.products.categories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.products.categories.ProductCategory;

import java.util.Optional;

public interface ProductCategoriesRepository extends JpaRepository<ProductCategory, Long> {
    public Optional<ProductCategory> findByName(String name);
}