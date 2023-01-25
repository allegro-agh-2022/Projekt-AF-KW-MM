package com.products.categories;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductCategoriesService {
    private final ProductCategoriesRepository categoriesRepository;

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
}
