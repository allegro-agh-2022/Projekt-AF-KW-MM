package service1 

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.annotation.Id
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.amqp.rabbit.annotation.RabbitHandler
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Service;

@SpringBootApplication
class ConsumerApplication

fun main(args: Array<String>) {
    runApplication<ConsumerApplication>(*args)
}

@RestController
class HelloController() {
    @GetMapping("/service1/hello")
    fun hello(): String = "Hello from service 1"
}

@Service
@RabbitListener(queues = ["hello"])
class RabbitReceiver {
    @RabbitHandler
    fun receiveFromHello(name: String) {
        println("Received: '$name'")
    }
}