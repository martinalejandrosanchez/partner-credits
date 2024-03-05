package com.pedidosya.partner_credits.application.loans

import arrow.core.Either
import com.pedidosya.partner_credits.application.core.SaveCredit
import com.pedidosya.partner_credits.application.notifications.SendMessage
import com.pedidosya.partner_credits.domain.MockitoHelper
import com.pedidosya.partner_credits.domain.exception.ConfirmCreditError
import com.pedidosya.partner_credits.domain.exception.SendEmailError
import com.pedidosya.partner_credits.domain.helper_functions.createCreditRequestMock
import com.pedidosya.partner_credits.domain.model.CreditRequest
import com.pedidosya.partner_credits.domain.ports.ExternalBankPort
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.kotlin.doAnswer
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

class CreditConfirmationTest {

    private lateinit var creditConfirmation: CreditConfirmation
    private val externalBankPort = mock<ExternalBankPort>()
    private val confirmCreditFn = externalBankPort::confirmCredit
    private val saveCredit = mock<SaveCredit>()
    private val sendMessage = mock<SendMessage>()
    private val creditRequest: CreditRequest = createCreditRequestMock()

    @BeforeEach
    fun setUp() {
        creditConfirmation = CreditConfirmation(confirmCreditFn, saveCredit, sendMessage)
    }

    @Test
    fun `successful credit confirmation`() {
        Mockito.`when`(confirmCreditFn(creditRequest))
            .thenReturn(Either.Right(true))

        creditConfirmation.confirmation(creditRequest)

        verify(externalBankPort, times(1)).confirmCredit(creditRequest)
        verify(sendMessage, times(1)).send(MockitoHelper.anyObject(), MockitoHelper.anyObject())
    }

    @Test
    fun `failed credit confirmation`() {
        Mockito.`when`(confirmCreditFn(creditRequest))
            .thenReturn(Either.Left(ConfirmCreditError("Connection Error")))

        assertThrows<ConfirmCreditError> {
            creditConfirmation.confirmation(creditRequest)
        }

        verify(externalBankPort, times(1)).confirmCredit(creditRequest)
        verify(sendMessage, times(0)).send(MockitoHelper.anyObject(), MockitoHelper.anyObject())
    }

    @Test
    fun `credit confirmation failed for another reason`() {
        Mockito.`when`(confirmCreditFn(creditRequest))
            .thenReturn(Either.Right(false))

        assertThrows<ConfirmCreditError> {
            creditConfirmation.confirmation(creditRequest)
        }

        verify(externalBankPort, times(1)).confirmCredit(creditRequest)
        verify(sendMessage, times(0)).send(MockitoHelper.anyObject(), MockitoHelper.anyObject())
    }
    @Test
    fun `sending credit confirmation mail failed`() {
        Mockito.`when`(confirmCreditFn(creditRequest))
            .thenReturn(Either.Right(true))

        doAnswer {
            throw SendEmailError("Connection Error")
        }.`when`(sendMessage).send(MockitoHelper.anyObject(), MockitoHelper.anyObject())

        assertThrows<SendEmailError> {
            creditConfirmation.confirmation(creditRequest)
        }

        verify(externalBankPort, times(1)).confirmCredit(creditRequest)
        verify(sendMessage, times(1)).send(MockitoHelper.anyObject(), MockitoHelper.anyObject())
    }
}