package com.pedidosya.partner_credits.application.loans

import arrow.core.Either
import arrow.core.right

import com.pedidosya.partner_credits.domain.exception.CreditDetailsError
import com.pedidosya.partner_credits.domain.helper_functions.createCreditDetailsMock
import com.pedidosya.partner_credits.domain.helper_functions.createInstallmentsListMock
import com.pedidosya.partner_credits.domain.model.Currency
import com.pedidosya.partner_credits.domain.ports.ExternalBankPort

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

import org.mockito.Mockito
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

import java.math.BigDecimal

class CreditSimulatorTest {

    private lateinit var creditSimulator: CreditSimulator
    private val externalBankPort = Mockito.mock<ExternalBankPort>()
    private val simulateCreditFn = externalBankPort::simulateCredit
    private val currency = Currency.ARS
    private val amount = BigDecimal("1000000")

    @BeforeEach
    fun setUp() {
        creditSimulator = CreditSimulator(simulateCreditFn)
    }

    @Test
    fun `ARG - successful credit simulation`() {
        val installmentPlanDetails = createInstallmentsListMock()
        val installments = installmentPlanDetails.size

        val creditDetails = createCreditDetailsMock(
            currency = currency,
            amount = amount,
            installments = installmentPlanDetails.size,
            installmentPlanDetails = installmentPlanDetails
        )

        Mockito.`when`(simulateCreditFn(currency, amount, installments))
            .thenReturn(creditDetails.right())

        creditSimulator.simulate(currency, amount, installments).let {
            assertNotNull(it)
            assertEquals(currency, it.currency)
            assertEquals(amount, it.amount)
            assertEquals(installments, it.installmentPlanDetails.size)
        }

        verify(externalBankPort, times(1)).simulateCredit(currency, amount, installments)
    }

    @Test
    fun `ARG - failed credit simulation`() {
        val installments = 12

        Mockito.`when`(simulateCreditFn(currency, amount, installments))
            .thenReturn(Either.Left(CreditDetailsError("Connection Error")))

        assertThrows<CreditDetailsError> {
            creditSimulator.simulate(currency, amount, installments)
        }

        verify(externalBankPort, times(1)).simulateCredit(currency, amount, installments)
    }
}