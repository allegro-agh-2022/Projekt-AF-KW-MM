package com.products.products;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.products.stores.Store;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product addProductToStore(Store store, ProductDto productDto) {
        Product product = new Product(productDto);
        product.setStore(store);
        productRepository.save(product);
        return product;
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public List<Product> getProductsByStoreId(Long storeId) {
        return productRepository.findAllByStoreId(storeId);
    }
}