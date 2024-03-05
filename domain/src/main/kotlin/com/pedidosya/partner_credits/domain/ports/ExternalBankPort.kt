package com.pedidosya.partner_credits.domain.ports

import arrow.core.Either
import com.pedidosya.partner_credits.domain.exception.ConfirmCreditError
import com.pedidosya.partner_credits.domain.exception.CreditDetailsError
import com.pedidosya.partner_credits.domain.model.CreditDetails
import com.pedidosya.partner_credits.domain.model.CreditRequest
import com.pedidosya.partner_credits.domain.model.Currency
import java.math.BigDecimal

interface ExternalBankPort {
    fun simulateCredit(currency: Currency, amount: BigDecimal, installments: Int): Either<CreditDetailsError, CreditDetails>
    fun confirmCredit(creditRequest: CreditRequest): Either<ConfirmCreditError, Boolean>
}