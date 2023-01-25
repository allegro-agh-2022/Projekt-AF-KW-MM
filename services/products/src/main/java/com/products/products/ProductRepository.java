package com.products;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
  @Query("SELECT p from products p WHERE p.store_id = ?1")
  List<Product> findAllByStoreId(Long storeId);
}