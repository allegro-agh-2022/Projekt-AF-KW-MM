package com.products.stores;

import lombok.RequiredArgsConstructor;
import java.util.Optional;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import com.products.products.Product;
import com.products.products.ProductDto;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class StoresController {
    private final StoresService storesService;

    @GetMapping("/stores")
    public ResponseEntity<List<Store>> getAllStores() {
        return ResponseEntity.ok().body(storesService.getAllStores());
    }

    @PostMapping("/stores")
    public ResponseEntity<Store> createStore(@RequestBody StoreDto storeDto) {
        return ResponseEntity.status(201).body(storesService.createStore(storeDto));
    }

    @GetMapping("/stores/{id}")
    public ResponseEntity<Optional<Store>> getStoreById(@PathVariable Long id){
        return ResponseEntity.ok().body(storesService.getStoreById(id));
    }

    @DeleteMapping("/stores/{id}")
    public ResponseEntity<Boolean> deleteStore(@PathVariable Long id) {
      storesService.deleteStore(id);
      return ResponseEntity.ok().body(true);
    }

    @GetMapping("/stores/{id}/products")
    public ResponseEntity<List<Product>> getStoreProducts(@PathVariable Long id) {
        return ResponseEntity.ok().body(storesService.getStoreProducts(id));
    }

    @PostMapping("/stores/{id}/products")
    public ResponseEntity<Product> createStore(
      @PathVariable Long id,
      @RequestBody ProductDto productDto
    ) {
        return ResponseEntity.status(201).body(storesService.addProductToStore(id, productDto));
    }
}
