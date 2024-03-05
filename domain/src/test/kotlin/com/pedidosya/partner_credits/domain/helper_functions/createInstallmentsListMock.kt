package com.pedidosya.partner_credits.domain.helper_functions

import com.pedidosya.partner_credits.domain.model.Installment
import java.math.BigDecimal

fun createInstallmentsListMock(): List<Installment> = listOf(
    createInstallmentMock(
        number = 1,
        amount = BigDecimal("189049.16"),
        interest = BigDecimal("122513.42"),
        rate = BigDecimal("35.43")
    ),
    createInstallmentMock(
        number = 2,
        amount = BigDecimal("187999.26"),
        interest = BigDecimal("117513.91"),
        rate = BigDecimal("35.43")
    ),
    createInstallmentMock(
        number = 3,
        amount = BigDecimal("186820.74"),
        interest = BigDecimal("111901.88"),
        rate = BigDecimal("35.43")
    ),
    createInstallmentMock(
        number = 4,
        amount = BigDecimal("185497.82"),
        interest = BigDecimal("105602.31"),
        rate = BigDecimal("35.43")
    ),
    createInstallmentMock(
        number = 5,
        amount = BigDecimal("184012.84"),
        interest = BigDecimal("98530.95"),
        rate = BigDecimal("35.43")
    ),
    createInstallmentMock(
        number = 6,
        amount = BigDecimal("182345.92"),
        interest = BigDecimal("90593.26"),
        rate = BigDecimal("35.43")
    ),
    createInstallmentMock(
        number = 7,
        amount = BigDecimal("180474.79"),
        interest = BigDecimal("81683.09"),
        rate = BigDecimal("35.43")
    ),
    createInstallmentMock(
        number = 8,
        amount = BigDecimal("178374.42"),
        interest = BigDecimal("71681.31"),
        rate = BigDecimal("35.43")
    ),
    createInstallmentMock(
        number = 9,
        amount = BigDecimal("176016.72"),
        interest = BigDecimal("60454.18"),
        rate = BigDecimal("35.43")
    ),
    createInstallmentMock(
        number = 10,
        amount = BigDecimal("173370.17"),
        interest = BigDecimal("47851.587"),
        rate = BigDecimal("35.43")
    ),
    createInstallmentMock(
        number = 11,
        amount = BigDecimal("170399.38"),
        interest = BigDecimal("129616.37"),
        rate = BigDecimal("35.43")
    ),
    createInstallmentMock(
        number = 12,
        amount = BigDecimal("167064.64"),
        interest = BigDecimal("145496.11"),
        rate = BigDecimal("35.43")
    )
)