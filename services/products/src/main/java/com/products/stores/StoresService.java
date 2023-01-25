package com.products.stores;

import com.products.products.Product;
import com.products.products.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.products.products.AddProductDto;

@Service
@RequiredArgsConstructor
public class StoresService {

    private final StoresRepository storesRepository;

    private final ProductService productService;

    public List<Store> getAllStores() {
        return storesRepository.findAll();
    }

    public Store createStore(StoreDto storeDto) {
        Store store = new Store(storeDto);
        storesRepository.save(store);

        return store;
    }

    public Optional<Store> getStoreById(Long id) {
        return storesRepository.findById(id);
    }

    public void deleteStore(Long id) {
        storesRepository.deleteById(id);
    }

    public List<Product> getStoreProducts(Long storeId) {
      return productService.getProductsByStoreId(storeId);
    }

    public Product addProductToStore(Long storeId, AddProductDto addProductDto) {
        Optional<Store> storeById = storesRepository.findById(storeId);
        if(storeById.isEmpty()){
            throw new IllegalStateException("No such store");
        }
        Store store = storeById.get();
        Product product = productService.addProductToStore(store, addProductDto);

        store.addProduct(product);
        storesRepository.save(store);

        return product;
    }
}
