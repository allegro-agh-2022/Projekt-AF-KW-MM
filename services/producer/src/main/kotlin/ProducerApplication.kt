package producer 

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.annotation.Id
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
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
    @GetMapping("/producer")
    fun hello(): String = "Hello from producer"
}

@RestController
class MessageResource(val rabbitTemplate: RabbitTemplate) {
    @PostMapping("/producer/{name}")
    fun sendMessageToHello(@PathVariable name: String): ResponseEntity<String> {
        rabbitTemplate.convertAndSend("hello", name)
        return ResponseEntity.ok(name)
    }
}
