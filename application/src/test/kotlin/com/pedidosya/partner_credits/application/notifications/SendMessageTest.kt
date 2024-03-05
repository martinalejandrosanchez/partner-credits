package com.pedidosya.partner_credits.application.notifications

import arrow.core.Either
import com.pedidosya.partner_credits.domain.exception.SendEmailError
import com.pedidosya.partner_credits.domain.ports.NotificationPort
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

class SendMessageTest {
    private lateinit var sendMessage: SendMessage
    private val notificationPort = mock<NotificationPort>()
    private val message = "test"
    private val mail = "test@pedidosya.com"

    @BeforeEach
    fun setUp() {
        sendMessage = SendMessage(notificationPort::send)
    }

    @Test
    fun `successful sending message`() {
        Mockito.`when`(notificationPort.send(message, mail))
            .thenReturn(Either.Right(true))

        sendMessage.send(message, mail)

        verify(notificationPort, times(1)).send(message, mail)
    }

    @Test
    fun `failed sending message`() {
        Mockito.`when`(notificationPort.send(message, mail))
            .thenReturn(Either.Left(SendEmailError("Connection Error")))

        assertThrows<SendEmailError> {
            sendMessage.send(message, mail)
        }

        verify(notificationPort, times(1)).send(message, mail)
    }
}