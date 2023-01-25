package com.products.stores;

import com.products.products.Product;
import lombok.RequiredArgsConstructor;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.products.products.AddProductDto;

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
      @RequestBody AddProductDto addProductDto
    ) {
        return ResponseEntity.status(201).body(storesService.addProductToStore(id, addProductDto));
    }
}
