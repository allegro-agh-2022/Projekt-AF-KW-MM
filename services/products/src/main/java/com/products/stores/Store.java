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

    // TODO: Change ot real user relation
    private String owner;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Product> products = new ArrayList<>();

    public Store(String name, String owner) {
        this.name = name;
        this.owner = owner;
    }

    public Store(StoreDto storeDto) {
      this.name = storeDto.name;
      this.owner = storeDto.owner;
    }

    public void addProduct(Product product) {
      products.add(product);
    }
}

