package com.pedidosya.partner_credits.domain.model

import java.math.BigDecimal
import java.util.UUID

class CreditRequest(
    uuid: UUID,
    currency: Currency,
    amount: BigDecimal,
    installments: Int,
    totalAmount: BigDecimal,
    installmentPlanDetails: List<Installment>,
    val mail: String
): CreditDetails(
    uuid, currency, amount, installments, totalAmount, installmentPlanDetails
)
