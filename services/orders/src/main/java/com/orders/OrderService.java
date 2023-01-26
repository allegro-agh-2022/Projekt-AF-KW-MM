package com.orders;


import com.order_item.AddOrderItemDto;
import com.order_item.OrderItem;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;


    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public Order getOrdersById(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.orElse(null);
    }

    public List<Order> getOrdersByUserId(Long userId) {
        Optional<List<Order>> orders = orderRepository.findAllByUserId(userId);
        return orders.orElse(new ArrayList<>());  // return empty list if no orders found
    }

    @Transactional
    public Order addOrder(AddOrderDto addOrderDto) {

        /* create query param string to get products array by id */
        String idsQueryParam = addOrderDto.orderItems.stream().reduce("", (partialString, element) -> partialString + element.productId + ",", String::concat);

        /* fetch products array by id */
        List<ProductDto> products = webClientBuilder.build()
                .get()
                .uri("http://products:8003/products/by-id-list",
                        uriBuilder -> uriBuilder.queryParam("ids", idsQueryParam).build())
                .retrieve()
                .bodyToFlux(ProductDto.class)
                .collectList()
                .block();


        /* check whether products stock is available */
        assert products != null;

        products.forEach(product -> {
            AddOrderItemDto currentOrderItem = addOrderDto.orderItems.stream()
                    .filter(orderItem -> orderItem.productId == product.id)
                    .findAny()
                    .orElse(null);

            assert currentOrderItem != null;

            if (product.stock - currentOrderItem.quantity < 0) {
                throw new RuntimeException("Not enough stock: " + product.name);
            }
        });

        /* update product stocks */
        products.forEach(product -> {
            AddOrderItemDto currentOrderItem = addOrderDto.orderItems.stream()
                    .filter(orderItem -> orderItem.productId == product.id)
                    .findAny()
                    .orElse(null);

            assert currentOrderItem != null;

            AddStockDto addStockDto = new AddStockDto();
            addStockDto.amount = -currentOrderItem.quantity;

            webClientBuilder.build()
                    .patch()
                    .uri("http://products:8003/products/{id}/stock", product.id)
                    .bodyValue(addStockDto)
                    .retrieve()
                    .bodyToMono(Void.class)
                    .block();
        });

        /* create new order */
        Order newOrder = new Order(addOrderDto);

        newOrder.setOrderItems(
                addOrderDto.orderItems
                        .stream()
                        .map(orderItem -> {
                            ProductDto productDto = products.stream().filter(product -> product.id == orderItem.productId).findAny().orElse(null);

                            OrderItem newOrderItem = new OrderItem();
                            newOrderItem.setItemPrice(productDto.price);
                            newOrderItem.setItemName(productDto.name);
                            newOrderItem.setQuantity(orderItem.quantity);
                            newOrderItem.setProductId(orderItem.productId);
                            newOrderItem.setOrder(newOrder);

                            return newOrderItem;
                        }).toList());

        /* save new order */
        orderRepository.save(newOrder);

        return newOrder;
    }
}
