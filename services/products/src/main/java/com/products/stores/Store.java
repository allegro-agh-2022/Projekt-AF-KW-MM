package com.products.stores;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

import com.products.products.Product;

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

