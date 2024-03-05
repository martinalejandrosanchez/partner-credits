package com.pedidosya.partner_credits.infrastructure.boundaries.outbound.postgres.mapper

interface EntityMapper<T, S> {
    fun toDomain(dboEntity: S): T

    fun toDbo(domainEntity: T): S
}