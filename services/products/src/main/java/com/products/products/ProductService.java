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

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        Optional<Product> productById = productRepository.findById(id);
        if (productById.isEmpty()) {
            throw new IllegalStateException("Such product does not exist");
        }

        return productById.get();
    }

    public Product updateProduct(Long id, UpdateProductDto productUpdate) {
        Product product = getProductById(id);
        product.applyUpdate(productUpdate);
        productRepository.save(product);
        return product;
    }

    public List<Product> getProductsByStoreId(Long storeId) {
        return productRepository.findAllByStoreId(storeId);
    }

    public Product addProductToStore(Store store, AddProductDto addProductDto) {
        Product product = new Product(addProductDto);
        product.setStore(store);
        productRepository.save(product);
        return product;
    }

    public Product increaseProductStock(Long productId, AddStockDto addStockDto) {
        Product product = getProductById(productId);
        product.addStock(addStockDto.getAmount());
        productRepository.save(product);
        return product;
    }
}