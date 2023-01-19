package producer 

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.annotation.Id
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}

@RestController
class MessageResource {
    @GetMapping("/")
    fun index(): String = "Hello world from producer index!"

    @GetMapping("/producer/")
    fun get1(): String = "Hello world form producer of path /producer/"

    @GetMapping("/producer/something/")
    fun get2(): String = "Hello world form producer of path /producer/something"
}
