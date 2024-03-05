package com.pedidosya.partner_credits.infrastructure.boundaries.inbound.rest


import com.pedidosya.partner_credits.application.loans.CreditConfirmation
import com.pedidosya.partner_credits.application.loans.CreditSimulator
import com.pedidosya.partner_credits.application.kyc.DataValidation
import com.pedidosya.partner_credits.domain.helper_functions.createCreditDetailsMock
import com.pedidosya.partner_credits.domain.helper_functions.createInstallmentsListMock
import com.pedidosya.partner_credits.domain.model.Currency

import com.fasterxml.jackson.databind.ObjectMapper

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content

import java.math.BigDecimal

@ExtendWith(SpringExtension::class)
@WebMvcTest(controllers = [CreditSimulatorController::class])
class CreditSimulatorControllerTest(
    @Autowired private val mockMvc: MockMvc,
    @Autowired private val objectMapper: ObjectMapper
) {
    @MockBean
    private lateinit var creditSimulator: CreditSimulator

    @MockBean
    private lateinit var creditConfirmation: CreditConfirmation

    @MockBean
    private lateinit var dataValidation: DataValidation

    @BeforeEach
    fun setUp() {
    }

    @Test
    fun simulate() {
        val currency = Currency.ARS
        val amount = BigDecimal("1000000")

        val installmentPlanDetails = createInstallmentsListMock()
        val installments = installmentPlanDetails.size

        val creditDetails = createCreditDetailsMock(
            currency = currency,
            amount = amount,
            installments = installmentPlanDetails.size,
            installmentPlanDetails = installmentPlanDetails
        )

        Mockito.`when`(creditSimulator.simulate(currency, amount, installments))
            .thenReturn(creditDetails)

        val resultActions: ResultActions = mockMvc.perform(
            MockMvcRequestBuilders.get("/credits/simulate?currency=$currency&amount=$amount&installments=$installments")
        )

        resultActions.andExpect(MockMvcResultMatchers.status().isOk)
        resultActions.andExpect(content().json(objectMapper.writeValueAsString(creditDetails)))
    }

    @Test
    fun validate() {
    }

    @Test
    fun confirm() {
    }
}