package producer 

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.annotation.Id
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable

@SpringBootApplication
class ProducerApplication

fun main(args: Array<String>) {
    runApplication<ProducerApplication>(*args)
}

@RestController
class HelloController() {
    @GetMapping("/producer/hello")
    fun hello(): String = "Hello from producer"
}

@RestController
class MessageResource(val rabbitTemplate: RabbitTemplate) {
    @GetMapping("/producer/order-status-changed/{name}")
    fun emitOrderStatusChanged(@PathVariable name: String): ResponseEntity<String> {
        rabbitTemplate.convertAndSend("order_status_changed", name)
        return ResponseEntity.ok(name)
    }

    @GetMapping("/producer/product-reviewed/{name}")
    fun emitProductReviewed(@PathVariable name: String): ResponseEntity<String> {
        rabbitTemplate.convertAndSend("product_reviewed", name)
        return ResponseEntity.ok(name)
    }
}
