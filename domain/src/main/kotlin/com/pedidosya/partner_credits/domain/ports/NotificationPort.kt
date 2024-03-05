package com.pedidosya.partner_credits.domain.ports

import arrow.core.Either
import com.pedidosya.partner_credits.domain.exception.SendEmailError

interface NotificationPort {
    fun send(message: String, mail: String): Either<SendEmailError, Boolean>
}