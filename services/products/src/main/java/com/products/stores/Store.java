package com.products.stores;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.products.products.Product;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;


@Table(name="stores")
@Entity
@NoArgsConstructor
@Data
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    private Long ownerId;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Product> products = new ArrayList<>();

    public Store(String name, Long ownerId) {
        this.name = name;
        this.ownerId = ownerId;
    }

    public Store(StoreDto storeDto) {
      this.name = storeDto.name;
      this.ownerId = storeDto.ownerId;
    }

    public void addProduct(Product product) {
      products.add(product);
    }
}

