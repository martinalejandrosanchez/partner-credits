package com.pedidosya.partner_credits.application.core

import arrow.core.Either
import com.pedidosya.partner_credits.domain.exception.SaveCreditError
import com.pedidosya.partner_credits.domain.helper_functions.createCreditRequestMock
import com.pedidosya.partner_credits.domain.model.CreditRequest
import com.pedidosya.partner_credits.domain.ports.CreditRequestPort
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

class SaveCreditTest {

    private lateinit var saveCredit: SaveCredit
    private val creditRequestPort = mock<CreditRequestPort>()
    private val creditRequest: CreditRequest = createCreditRequestMock()

    @BeforeEach
    fun setUp() {
        saveCredit = SaveCredit(creditRequestPort::save)
    }

    @Test
    fun `successful credit data saving`() {
        Mockito.`when`(creditRequestPort.save(creditRequest))
            .thenReturn(Either.Right(true))

        saveCredit.save(creditRequest)

        verify(creditRequestPort, times(1)).save(creditRequest)
    }

    @Test
    fun `failed credit data saving`() {
        Mockito.`when`(creditRequestPort.save(creditRequest))
            .thenReturn(Either.Left(SaveCreditError("Connection Error")))

        assertThrows<SaveCreditError> {
            saveCredit.save(creditRequest)
        }

        verify(creditRequestPort, times(1)).save(creditRequest)
    }
}