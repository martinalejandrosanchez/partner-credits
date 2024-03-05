package com.pedidosya.partner_credits.infrastructure.boundaries.outbound.apiclient

import arrow.core.Either
import com.pedidosya.partner_credits.domain.exception.ConfirmCreditError
import com.pedidosya.partner_credits.domain.exception.CreditDetailsError
import com.pedidosya.partner_credits.domain.model.CreditDetails
import com.pedidosya.partner_credits.domain.model.CreditRequest
import com.pedidosya.partner_credits.domain.model.Currency
import com.pedidosya.partner_credits.domain.model.Installment
import com.pedidosya.partner_credits.domain.ports.ExternalBankPort
import com.pedidosya.partner_credits.loggerFor
import org.springframework.stereotype.Component
import java.math.BigDecimal
import java.util.UUID

@Component
class ExternalBankApiClientAdapter: ExternalBankPort {
    companion object {
        private val logger = loggerFor<ExternalBankApiClientAdapter>()
    }

    override fun simulateCredit(
        currency: Currency,
        amount: BigDecimal,
        installments: Int
    ): Either<CreditDetailsError, CreditDetails> {
        logger.debug("Simulating Credit - {currency: {}, amount: {}, installments: {}}", currency, amount, installments)

        val creditDetails = CreditDetails(
            uuid = UUID.fromString("795b5235-49a8-4082-a78b-901b8661e939"),
            currency = Currency.ARS,
            amount = BigDecimal("104309.5"),
            installments = 12,
            totalAmount = BigDecimal("400000"),
            installmentPlanDetails = listOf(
                Installment(
                    number = 1,
                    amount = BigDecimal("189049.16"),
                    interest = BigDecimal("122513.42"),
                    rate = BigDecimal("35.43")
                ),
                Installment(
                    number = 2,
                    amount = BigDecimal("187999.26"),
                    interest = BigDecimal("117513.91"),
                    rate = BigDecimal("35.43")
                ),
                Installment(
                    number = 3,
                    amount = BigDecimal("186820.74"),
                    interest = BigDecimal("111901.88"),
                    rate = BigDecimal("35.43")
                ),
                Installment(
                    number = 4,
                    amount = BigDecimal("185497.82"),
                    interest = BigDecimal("105602.31"),
                    rate = BigDecimal("35.43")
                ),
                Installment(
                    number = 5,
                    amount = BigDecimal("184012.84"),
                    interest = BigDecimal("98530.95"),
                    rate = BigDecimal("35.43")
                ),
                Installment(
                    number = 6,
                    amount = BigDecimal("182345.92"),
                    interest = BigDecimal("90593.26"),
                    rate = BigDecimal("35.43")
                ),
                Installment(
                    number = 7,
                    amount = BigDecimal("180474.79"),
                    interest = BigDecimal("81683.09"),
                    rate = BigDecimal("35.43")
                ),
                Installment(
                    number = 8,
                    amount = BigDecimal("178374.42"),
                    interest = BigDecimal("71681.31"),
                    rate = BigDecimal("35.43")
                ),
                Installment(
                    number = 9,
                    amount = BigDecimal("176016.72"),
                    interest = BigDecimal("60454.18"),
                    rate = BigDecimal("35.43")
                ),
                Installment(
                    number = 10,
                    amount = BigDecimal("173370.17"),
                    interest = BigDecimal("47851.587"),
                    rate = BigDecimal("35.43")
                ),
                Installment(
                    number = 11,
                    amount = BigDecimal("170399.38"),
                    interest = BigDecimal("129616.37"),
                    rate = BigDecimal("35.43")
                ),
                Installment(
                    number = 12,
                    amount = BigDecimal("167064.64"),
                    interest = BigDecimal("145496.11"),
                    rate = BigDecimal("35.43")
                )
            )
        )
        return Either.Right(creditDetails)
    }

    override fun confirmCredit(creditRequest: CreditRequest): Either<ConfirmCreditError, Boolean> {
        logger.debug("Sending credit request {}", creditRequest)
        return Either.Right(true)
    }
}