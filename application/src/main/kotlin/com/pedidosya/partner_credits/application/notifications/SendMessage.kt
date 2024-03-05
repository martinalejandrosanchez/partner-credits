package com.pedidosya.partner_credits.application.notifications

import arrow.core.Either
import com.pedidosya.partner_credits.domain.exception.SendEmailError
import com.pedidosya.partner_credits.loggerFor

class SendMessage(
    private val sendFn: (message: String, mail: String) -> Either<SendEmailError, Boolean>
) {
    companion object {
        private val logger = loggerFor<SendMessage>()
    }

    fun send(message: String, mail: String) {
        logger.debug("Sending credit confirmation mail - mail {} message {}", mail, message)
        sendFn(message, mail).fold(
            ifLeft = {
                logger.error("An error occurred while sending message - ex {}", it.message)
                throw it
            },
            ifRight = {
                logger.debug("The message was sent successfully - mail {} message {}", mail, message)
            }
        )
    }
}