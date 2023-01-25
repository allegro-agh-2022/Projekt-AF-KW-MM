package com.order_item;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.orders.Order;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="orders_items")
@Entity
@NoArgsConstructor
@Data
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name="order_id", nullable=false)
    @JsonIgnore
    private Order orderId;

    private long productId;

    private String itemName;

    private double itemPrice;

    private int quantity;
}
