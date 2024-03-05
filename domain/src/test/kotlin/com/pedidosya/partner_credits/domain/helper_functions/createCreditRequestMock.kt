package com.pedidosya.partner_credits.domain.helper_functions

import com.pedidosya.partner_credits.domain.model.CreditRequest
import com.pedidosya.partner_credits.domain.model.Currency
import com.pedidosya.partner_credits.domain.model.Installment
import java.math.BigDecimal
import java.util.UUID

fun createCreditRequestMock(
    id: UUID = UUID.fromString("795b5235-49a8-4082-a78b-901b8661e939"),
    currency: Currency = Currency.ARS,
    amount: BigDecimal = BigDecimal(1),
    installments: Int = 12,
    totalAmount: BigDecimal = BigDecimal(1000000),
    installmentPlanDetails: List<Installment> = createInstallmentsListMock(),
    mail: String = "test@pedidosya.com"
) = CreditRequest(
    id, currency, amount, installments, totalAmount, installmentPlanDetails, mail
)