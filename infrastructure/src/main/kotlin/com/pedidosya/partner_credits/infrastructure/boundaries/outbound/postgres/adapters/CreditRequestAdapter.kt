package com.pedidosya.partner_credits.infrastructure.boundaries.outbound.postgres.adapters

import arrow.core.Either
import com.pedidosya.partner_credits.domain.exception.SaveCreditError
import com.pedidosya.partner_credits.domain.model.CreditRequest
import com.pedidosya.partner_credits.domain.ports.CreditRequestPort
import com.pedidosya.partner_credits.infrastructure.boundaries.outbound.postgres.mapper.CreditRequestMapper
import com.pedidosya.partner_credits.infrastructure.boundaries.outbound.postgres.repositories.CreditRequestRepository
import com.pedidosya.partner_credits.loggerFor
import org.springframework.stereotype.Component

@Component
class CreditRequestAdapter(
    private val repository: CreditRequestRepository,
    private val mapper: CreditRequestMapper
): CreditRequestPort {
    companion object {
        private val logger = loggerFor<CreditRequestAdapter>()
    }

    override fun save(creditRequest: CreditRequest): Either<SaveCreditError, Boolean> {
        return try {
            val dboEntity = mapper.toDbo(creditRequest)
            repository.save(dboEntity)
            Either.Right(true)
        } catch (ex: Exception) {
            val message = "An error ocurred while saving the credit request - $creditRequest - exception: ${ex.message}"
            logger.error(message)
            Either.Left(SaveCreditError(message))
        }
    }
}