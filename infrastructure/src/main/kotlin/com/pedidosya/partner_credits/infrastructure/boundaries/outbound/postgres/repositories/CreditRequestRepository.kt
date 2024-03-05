package com.pedidosya.partner_credits.infrastructure.boundaries.outbound.postgres.repositories

import com.pedidosya.partner_credits.infrastructure.boundaries.outbound.postgres.entities.CreditRequestEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CreditRequestRepository: JpaRepository<CreditRequestEntity, Long>