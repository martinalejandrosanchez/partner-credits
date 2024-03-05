package com.pedidosya.partner_credits.domain.helper_functions

import com.pedidosya.partner_credits.domain.model.Installment
import java.math.BigDecimal

fun createInstallmentMock(
    number: Int = 1,
    amount: BigDecimal = BigDecimal("14000"),
    interest: BigDecimal = BigDecimal("4000"),
    rate: BigDecimal = BigDecimal("400")
) = Installment(
    number,
    amount,
    interest,
    rate
)