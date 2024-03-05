package com.pedidosya.partner_credits.infrastructure.boundaries.outbound.postgres.mapper

import com.pedidosya.partner_credits.domain.model.CreditRequest
import com.pedidosya.partner_credits.domain.model.Currency
import com.pedidosya.partner_credits.infrastructure.boundaries.outbound.postgres.entities.CreditRequestEntity
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class CreditRequestMapper(
    private val interestMapper: InstallmentMapper
): EntityMapper<CreditRequest, CreditRequestEntity> {
    override fun toDomain(dboEntity: CreditRequestEntity): CreditRequest {
        return CreditRequest(
            uuid = UUID.fromString(dboEntity.uuid),
            currency = Currency.valueOf(dboEntity.currency),
            installments = dboEntity.installments,
            mail = dboEntity.mail,
            amount = dboEntity.amount,
            totalAmount = dboEntity.totalAmount,
            installmentPlanDetails = dboEntity.installmentPlanDetails.map {
                interestMapper.toDomain(it)
            }.toList()
        )
    }

    override fun toDbo(domainEntity: CreditRequest): CreditRequestEntity {
        return CreditRequestEntity(
            uuid = domainEntity.uuid.toString(),
            currency = domainEntity.currency.name,
            installments = domainEntity.installments,
            mail = domainEntity.mail,
            amount = domainEntity.amount,
            totalAmount = domainEntity.totalAmount,
            installmentPlanDetails = domainEntity.installmentPlanDetails.map {
                interestMapper.toDbo(it)
            }.toList()
        )
    }
}