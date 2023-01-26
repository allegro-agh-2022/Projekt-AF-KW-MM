package com.products.categories;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductCategoriesService {
    private final ProductCategoriesRepository categoriesRepository;

    @Cacheable(value = "categories")
    public List<ProductCategory> getAllCategories() {
        return categoriesRepository.findAll();
    }

    public ProductCategory createCategory(AddProductCategoryDto categoryDto) {
        ProductCategory category = new ProductCategory(categoryDto);
        categoriesRepository.save(category);
        return category;
    }

    public ProductCategory getCategoryByd(Long id) {
        Optional<ProductCategory> categoryById = categoriesRepository.findById(id);
        if (categoryById.isEmpty()) {
            throw new IllegalStateException("Such product category does not exist");
        }
        return categoryById.get();
    }

    public ProductCategory getCategoryByName(String name) {
        Optional<ProductCategory> categoryByName = categoriesRepository.findByName(name);
        if (categoryByName.isEmpty()) {
            throw new IllegalStateException("Such product category does not exist");
        }
        return categoryByName.get();
    }
}
