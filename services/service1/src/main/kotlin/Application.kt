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
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}

@RestController
class MessageResource() {
    @GetMapping("/service1/")
    fun get1(): String = "Hello world form producer of path /producer/"
}

@Service
@RabbitListener(queues = ["hello"])
class RabbitReceiver {
    @RabbitHandler
    fun receive(name: String) {
        println("Received: '$name'")
    }
}