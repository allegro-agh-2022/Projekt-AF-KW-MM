package com.mailer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

class MessageReceiver {
    @Autowired
    private EmailService emailSender;

    @RabbitListener(queues="order_status_changed")
    public void onOrderStatusChanged(String name) {
        System.out.println(String.format("[order_status_changed] Received message: %s", name));
//        emailSender.sendMail("adif367@gmail.com", "Test Subject", name);
    }

    @RabbitListener(queues="product_reviewed")
    public void onProductReviewed(String name) {
        System.out.println(String.format("[product_reviewed] Received message: %s", name));
//        emailSender.sendMail("adif367@gmail.com", "Test Subject", name);
    }
}