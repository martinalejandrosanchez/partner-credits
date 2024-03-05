package com.pedidosya.partner_credits.infrastructure.boundaries.outbound.postgres.mapper

import com.pedidosya.partner_credits.domain.model.Installment
import com.pedidosya.partner_credits.infrastructure.boundaries.outbound.postgres.entities.InstallmentEntity
import org.springframework.stereotype.Component

@Component
class InstallmentMapper: EntityMapper<Installment, InstallmentEntity> {
    override fun toDomain(dboEntity: InstallmentEntity): Installment {
        return Installment(
            number = dboEntity.number,
            amount = dboEntity.amount,
            interest = dboEntity.interest,
            rate = dboEntity.rate
        )
    }

    override fun toDbo(domainEntity: Installment): InstallmentEntity {
        return InstallmentEntity(
            number = domainEntity.number,
            amount = domainEntity.amount,
            interest = domainEntity.interest,
            rate = domainEntity.rate
        )
    }
}