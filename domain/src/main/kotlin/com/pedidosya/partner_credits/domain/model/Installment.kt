package com.pedidosya.partner_credits.domain.model

import java.math.BigDecimal

data class Installment(
    val number: Int,
    val amount: BigDecimal,
    val interest: BigDecimal,
    val rate: BigDecimal
)
