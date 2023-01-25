package com.products.products;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductsRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByStoreId(Long storeId);

    List<Product> findAllByCategoryId(Long categoryId);

    List<Product> findByIdIn(List<Long> ids);
}