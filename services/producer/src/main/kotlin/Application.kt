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
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}

@RestController
class MessageResource(val rabbitTemplate: RabbitTemplate) {
    @GetMapping("/")
    fun index(): String = "Index"

    @PostMapping("/producer/{name}")
    fun postPerson(@PathVariable name: String): ResponseEntity<String> {
        rabbitTemplate.convertAndSend("hello", name)
        return ResponseEntity.ok(name)
    }
}
