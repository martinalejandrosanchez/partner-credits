package com.pedidosya.partner_credits.infrastructure.boundaries.outbound.apiclient

import arrow.core.Either
import com.pedidosya.partner_credits.domain.exception.SendEmailError
import com.pedidosya.partner_credits.domain.ports.NotificationPort
import com.pedidosya.partner_credits.loggerFor
import org.springframework.stereotype.Component

@Component
class NotificationsApiClientAdapter: NotificationPort {
    companion object {
        private val logger = loggerFor<NotificationsApiClientAdapter>()
    }

    override fun send(message: String, mail: String): Either<SendEmailError, Boolean> {
        logger.debug("Sending credit confirmation mail {}", mail)
        return Either.Right(true)
    }
}