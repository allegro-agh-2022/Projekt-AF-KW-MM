package com.orders;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.order_item.OrderItem;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Table(name="orders")
@Entity
@NoArgsConstructor
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    private long userId;

    private long storeId;

    private Date date;

    private String status;

    public String address;

    public Order(AddOrderDto addOrderDto) {
        this.userId = addOrderDto.userId;
        this.storeId = addOrderDto.storeId;
        this.address = addOrderDto.address;
        this.date = new Date();
        this.status = "new";
    }
}
