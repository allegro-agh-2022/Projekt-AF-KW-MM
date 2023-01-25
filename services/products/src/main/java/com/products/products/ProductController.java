package com.products.products;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts(@RequestParam(required = false) String category) {
        if (category != null) {
            return ResponseEntity.ok().body(productService.getProductsByCategory(category));
        }
        return ResponseEntity.ok().body(productService.getProducts());
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        return ResponseEntity.ok().body(productService.getProductById(id));
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody UpdateProductDto updateProductDto) {
        return ResponseEntity.ok().body(productService.updateProduct(id, updateProductDto));
    }

    @PatchMapping("/products/{id}/stock")
    public ResponseEntity<Product> increaseProductStock(@PathVariable Long id, @RequestBody AddStockDto addStockDto) {
        return ResponseEntity.ok().body(productService.increaseProductStock(id, addStockDto));
    }
}
