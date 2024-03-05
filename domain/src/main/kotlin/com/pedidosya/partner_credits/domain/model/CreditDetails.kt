package com.pedidosya.partner_credits.domain.model

import java.math.BigDecimal
import java.util.UUID

open class CreditDetails(
    val uuid: UUID,
    val currency: Currency,
    val amount: BigDecimal,
    val installments: Int,
    val totalAmount: BigDecimal,
    val installmentPlanDetails: List<Installment>
)
