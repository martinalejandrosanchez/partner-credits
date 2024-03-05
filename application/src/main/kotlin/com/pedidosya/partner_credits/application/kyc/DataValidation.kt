package com.pedidosya.partner_credits.application.kyc

import arrow.core.Either
import arrow.core.getOrElse
import com.pedidosya.partner_credits.domain.exception.ValidateDataError
import com.pedidosya.partner_credits.loggerFor

class DataValidation(
    private val validateDataFn: (mail: String, phone: String, contract: String) -> Either<ValidateDataError, Boolean>
) {
    companion object {
        private val logger = loggerFor<DataValidation>()
    }

    fun validate(mail: String, phone: String, contract: String): Boolean {
        logger.debug("Validating Data - {mail:$mail, phone:$phone, contract:$contract}")
        return validateDataFn(mail, phone, contract).getOrElse {
            logger.error("An error occurred while validate data - ex {}", it.message)
            throw it
        }.also {
            logger.debug("Validate Data Response - {}", it)
        }
    }
}
