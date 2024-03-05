package com.pedidosya.partner_credits.infrastructure.boundaries.outbound.apiclient

import arrow.core.Either
import com.pedidosya.partner_credits.domain.exception.ValidateDataError
import com.pedidosya.partner_credits.domain.ports.KycPort
import com.pedidosya.partner_credits.loggerFor
import org.springframework.stereotype.Component

@Component
class KycApiClientAdapter: KycPort {
    companion object {
        private val logger = loggerFor<KycApiClientAdapter>()
    }

    override fun validateData(mail: String, phone: String, contract: String): Either<ValidateDataError, Boolean> {
        logger.debug("Validating Data - {mail:$mail, phone:$phone, contract:$contract}")
        return Either.Right(true)
    }
}