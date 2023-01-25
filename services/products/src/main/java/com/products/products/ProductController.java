package com.products;


import lombok.RequiredArgsConstructor;
import java.util.Optional;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
// @RequestMapping(path = "products")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/products/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok().body("Java jak ja Cię nienawidzę...");
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts() {
        return ResponseEntity.ok().body(productService.getProducts());
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Optional<Product>> getProduct(@PathVariable Long id){
        return ResponseEntity.ok().body(productService.getProductById(id));
    }
}
