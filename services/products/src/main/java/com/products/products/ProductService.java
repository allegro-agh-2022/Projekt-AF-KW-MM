package com.products.products;

import com.products.categories.ProductCategoriesService;
import com.products.categories.ProductCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.products.stores.Store;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductsRepository productsRepository;

    private final ProductCategoriesService categoriesService;

    public List<Product> getProducts() {
        return productsRepository.findAll();
    }

    public List<Product> getProductsByCategory(String categoryName) {
        ProductCategory category = categoriesService.getCategoryByName(categoryName);
        return productsRepository.findAllByCategoryId(category.getId());
    }

    public Product getProductById(Long id) {
        Optional<Product> productById = productsRepository.findById(id);
        if (productById.isEmpty()) {
            throw new IllegalStateException("Such product does not exist");
        }

        return productById.get();
    }

    public Product updateProduct(Long id, UpdateProductDto productUpdate) {
        Product product = getProductById(id);
        product.applyUpdate(productUpdate);
        if (product.getCategory().getId() != productUpdate.getCategoryId()) {
            ProductCategory newCategory = categoriesService.getCategoryByd(productUpdate.getCategoryId());
            product.setCategory(newCategory);
        }

        productsRepository.save(product);
        return product;
    }

    public List<Product> getProductsByStoreId(Long storeId) {
        return productsRepository.findAllByStoreId(storeId);
    }

    public Product addProductToStore(Store store, AddProductDto addProductDto) {
        Product product = new Product(addProductDto);
        ProductCategory category = categoriesService.getCategoryByd(addProductDto.getCategoryId());
        product.setStore(store);
        product.setCategory(category);
        productsRepository.save(product);
        return product;
    }

    public Product increaseProductStock(Long productId, AddStockDto addStockDto) {
        Product product = getProductById(productId);
        product.addStock(addStockDto.getAmount());
        productsRepository.save(product);
        return product;
    }

    public List<Product> getByIdList(List<Long> idList) {
        return productsRepository.findByIdIn(idList);
    }
}