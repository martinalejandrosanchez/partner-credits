package com.pedidosya.partner_credits.application.loans

import arrow.core.Either
import arrow.core.getOrElse
import com.pedidosya.partner_credits.domain.exception.CreditDetailsError
import com.pedidosya.partner_credits.domain.model.CreditDetails
import com.pedidosya.partner_credits.domain.model.Currency
import com.pedidosya.partner_credits.loggerFor
import java.math.BigDecimal

class CreditSimulator(
    private val simulateCreditFn: (currency: Currency, amount: BigDecimal, installments: Int) -> Either<CreditDetailsError, CreditDetails>
) {
    companion object {
        private val logger = loggerFor<CreditSimulator>()
    }

    fun simulate(currency: Currency, amount: BigDecimal, installments: Int): CreditDetails {
        logger.debug("Simulating Credit - {currency: {}, amount: {}, installments: {}}", currency, amount, installments)
        return simulateCreditFn(currency, amount, installments).getOrElse {
            logger.error("An error occurred while simulating the credit - ex {}", it.message)
            throw it
        }.also {
            logger.debug("Simulating Credit Response - {}", it)
        }
    }
}