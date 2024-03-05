package com.pedidosya.partner_credits.application.kyc

import arrow.core.Either
import com.pedidosya.partner_credits.domain.exception.ValidateDataError
import com.pedidosya.partner_credits.domain.ports.KycPort
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.kotlin.verify

class DataValidationTest {
    private lateinit var dataValidation: DataValidation
    private val kycPort = mock<KycPort>()
    private val validateDataFn = kycPort::validateData
    private val mail = "test@pedidosya.com"
    private val phone = "+541122406680"
    private val contract = "02a7a454-ef9d-46b6-b10e-144c26a16ff2"

    @BeforeEach
    fun setUp() {
        dataValidation = DataValidation(validateDataFn)
    }

    @Test
    fun `successful data validation`() {
        Mockito.`when`(validateDataFn(mail, phone, contract))
            .thenReturn(Either.Right(true))

        assertEquals(true, dataValidation.validate(mail, phone, contract))

        verify(kycPort, times(1)).validateData(mail, phone, contract)
    }

    @Test
    fun `data validation failed`() {
        Mockito.`when`(validateDataFn(mail, phone, contract))
            .thenReturn(Either.Left(ValidateDataError("Connection Error")))

        assertThrows<ValidateDataError> {
            dataValidation.validate(mail, phone, contract)
        }

        verify(kycPort, times(1)).validateData(mail, phone, contract)
    }
}