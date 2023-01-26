package com.products.stores;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.products.products.Product;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Table(name="stores")
@Entity
@Data
public class Store implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    private Long ownerId;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Product> products = new ArrayList<>();

    @JsonCreator
    public Store() {
        super();
    }

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

