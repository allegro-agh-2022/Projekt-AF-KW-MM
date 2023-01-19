package service1 

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
    fun index(): String = "Hello world from service1 index"

    @GetMapping("/service1/")
    fun get1(): String = "Hello world form service1 of path /service1/"

    @GetMapping("/service1/something/")
    fun get2(): String = "Hello world form service1 of path /service1/something"
}
