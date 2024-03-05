package com.pedidosya.partner_credits

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["com.pedidosya"])
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}