package com.orders;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;


    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    @Transactional
    public List<ProductDto> addOrder(AddOrderDto addOrderDto) {
        String idsQueryParam = addOrderDto.orderItems.stream().reduce("", (partialString, element) -> partialString + element.productId + ",", String::concat);

        List<ProductDto> products = webClientBuilder.build()
                .get()
                .uri("http://products:8003/products/by-id-list",
                        uriBuilder -> uriBuilder.queryParam("ids", idsQueryParam).build())
                .retrieve()
                .bodyToFlux(ProductDto.class)
                .collectList()
                .block();

//        assert products != null;
//
//        products.forEach(product -> {
//            addOrderDto.orderItems.stream()
//                    .filter(orderItem -> orderItem.productId == product.id)
//                    .findFirst()
//                    .ifPresent(orderItem -> product.stock -= orderItem.quantity);
//        });





//
//        Order newOrder = new Order(addOrderDto, products);
//
//        orderRepository.save(newOrder);

        return products;
    }
}
