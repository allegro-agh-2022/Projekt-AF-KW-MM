package com.products.categories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.products.categories.ProductCategory;

public interface ProductCategoriesRepository extends JpaRepository<ProductCategory, Long> {}