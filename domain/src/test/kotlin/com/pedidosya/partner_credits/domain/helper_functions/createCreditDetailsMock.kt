package com.pedidosya.partner_credits.domain.helper_functions

import com.pedidosya.partner_credits.domain.model.CreditDetails
import com.pedidosya.partner_credits.domain.model.Currency
import com.pedidosya.partner_credits.domain.model.Installment
import java.math.BigDecimal
import java.util.UUID

fun createCreditDetailsMock(
    id: UUID = UUID.fromString("795b5235-49a8-4082-a78b-901b8661e939"),
    currency: Currency = Currency.ARS,
    amount: BigDecimal = BigDecimal("104309.5"),
    installments: Int = 12,
    totalAmount: BigDecimal = BigDecimal("400000"),
    installmentPlanDetails: List<Installment> = listOf()
) = CreditDetails(
    id,
    currency,
    amount,
    installments,
    totalAmount,
    installmentPlanDetails
)