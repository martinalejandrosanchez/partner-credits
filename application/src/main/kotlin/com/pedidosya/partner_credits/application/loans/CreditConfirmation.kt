package com.pedidosya.partner_credits.application.loans

import arrow.core.Either
import com.pedidosya.partner_credits.application.core.SaveCredit
import com.pedidosya.partner_credits.application.notifications.SendMessage
import com.pedidosya.partner_credits.domain.exception.ConfirmCreditError
import com.pedidosya.partner_credits.domain.model.CreditRequest
import com.pedidosya.partner_credits.loggerFor

class CreditConfirmation(
    private val confirmCreditFn: (creditRequest: CreditRequest) -> Either<ConfirmCreditError, Boolean>,
    private val saveCredit: SaveCredit,
    private val sendMessage: SendMessage
) {
    companion object {
        private val logger = loggerFor<CreditConfirmation>()
    }

    fun confirmation(creditRequest: CreditRequest) {
        logger.debug("Sending credit request {}", creditRequest)

        val confirmCreditErrorMessage = "An error occurred while confirm credit - $creditRequest"

        confirmCreditFn(creditRequest).fold(
            ifRight = {success ->
                if (!success) {
                    logger.error(confirmCreditErrorMessage)
                    throw ConfirmCreditError(confirmCreditErrorMessage)
                }

                saveCredit.save(creditRequest)

                val message = "Approved Credit!"
                sendMessage.send(message, creditRequest.mail)
            },
            ifLeft = {
                logger.error("$confirmCreditErrorMessage - ex {}", it.message)
                throw it
            }
        )
    }
}