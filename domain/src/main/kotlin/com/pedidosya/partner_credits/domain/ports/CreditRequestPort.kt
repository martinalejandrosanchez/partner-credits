package com.pedidosya.partner_credits.domain.ports

import arrow.core.Either
import com.pedidosya.partner_credits.domain.exception.SaveCreditError
import com.pedidosya.partner_credits.domain.model.CreditRequest

interface CreditRequestPort {
    fun save(creditRequest: CreditRequest): Either<SaveCreditError, Boolean>
}