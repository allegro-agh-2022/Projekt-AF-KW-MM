package com.products.categories;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProductCategoriesController {
    private final ProductCategoriesService categoriesService;

    @GetMapping("/product-categories")
    public ResponseEntity<List<ProductCategory>> getAllCategories() {
        return ResponseEntity.ok().body(categoriesService.getAllCategories());
    }

    @PostMapping("/product-categories")
    public ResponseEntity<ProductCategory> createCategory(@RequestBody AddProductCategoryDto categoryDto) {
        return ResponseEntity.status(201).body(categoriesService.createCategory(categoryDto));
    }

    @GetMapping("/product-categories/{id}")
    public ResponseEntity<ProductCategory> getCategoryById(@PathVariable Long id){
        return ResponseEntity.ok().body(categoriesService.getCategoryByd(id));
    }
}
