package com.pedidosya.partner_credits.domain.ports

import arrow.core.Either
import com.pedidosya.partner_credits.domain.exception.ValidateDataError

interface KycPort {
    fun validateData(mail: String, phone: String, contract: String): Either<ValidateDataError, Boolean>
}