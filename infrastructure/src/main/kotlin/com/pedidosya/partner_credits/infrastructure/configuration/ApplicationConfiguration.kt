package com.pedidosya.partner_credits.infrastructure.configuration

import com.pedidosya.partner_credits.application.loans.CreditConfirmation
import com.pedidosya.partner_credits.application.loans.CreditSimulator
import com.pedidosya.partner_credits.application.kyc.DataValidation
import com.pedidosya.partner_credits.application.core.SaveCredit
import com.pedidosya.partner_credits.application.notifications.SendMessage
import com.pedidosya.partner_credits.domain.ports.CreditRequestPort
import com.pedidosya.partner_credits.domain.ports.ExternalBankPort
import com.pedidosya.partner_credits.domain.ports.KycPort
import com.pedidosya.partner_credits.domain.ports.NotificationPort
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ApplicationConfiguration {

    @Bean
    internal fun creditSimulator(
        externalBankPort: ExternalBankPort
    ) = CreditSimulator(externalBankPort::simulateCredit)

    @Bean
    internal fun dataValidation(
        kycPort: KycPort
    ) = DataValidation(kycPort::validateData)

    @Bean
    internal fun creditConfirmation(
        externalBankPort: ExternalBankPort,
        saveCredit: SaveCredit,
        sendMessage: SendMessage
    ) = CreditConfirmation(
        externalBankPort::confirmCredit,
        saveCredit,
        sendMessage
    )

    @Bean
    internal fun saveCredit(
        creditRequestPort: CreditRequestPort
    ) = SaveCredit(
        creditRequestPort::save
    )

    @Bean
    internal fun sendMessage(
        notificationPort: NotificationPort
    ) = SendMessage(
        notificationPort::send
    )
}
