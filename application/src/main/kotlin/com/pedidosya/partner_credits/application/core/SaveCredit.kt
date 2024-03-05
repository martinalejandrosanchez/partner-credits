package com.pedidosya.partner_credits.application.core

import arrow.core.Either
import com.pedidosya.partner_credits.domain.exception.SaveCreditError
import com.pedidosya.partner_credits.domain.model.CreditRequest
import com.pedidosya.partner_credits.loggerFor

class SaveCredit(
    private val saveFn: (creditRequest: CreditRequest) -> Either<SaveCreditError, Boolean>
) {
    companion object {
        private val logger = loggerFor<SaveCredit>()
    }

    fun save(creditRequest: CreditRequest) {
        logger.debug("Saving credit application data {}", creditRequest)
        saveFn(creditRequest).fold(
            ifLeft = {
                logger.error("An error occurred while saving the credit application data - ex {}", it.message)
                throw it
            },
            ifRight = {
                logger.debug("Successful credit data saving - creditRequest {}", creditRequest)
            }
        )
    }
}