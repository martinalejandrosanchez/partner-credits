package com.pedidosya.partner_credits.infrastructure.boundaries.inbound.rest

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HealthCheckController {

    @GetMapping("/health")
    fun healthCheck(): Map<String, String> = mapOf("status" to "UP")

    @GetMapping("/info")
    fun info(): Map<String, String> = mapOf("app" to "1.0.0")
}