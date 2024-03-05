package com.pedidosya.partner_credits.infrastructure.boundaries.outbound.postgres.entities

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import java.math.BigDecimal

@Entity
class InstallmentEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "NUMBER", nullable = false)
    val number: Int,

    @Column(name = "AMOUNT", nullable = false)
    val amount: BigDecimal,

    @Column(name = "INTEREST", nullable = false)
    val interest: BigDecimal,

    @Column(name = "RATE", nullable = false)
    val rate: BigDecimal,

    @ManyToOne(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    @JoinColumn(name = "credit_request_id", insertable = false, updatable = false)
    var creditRequestEntity: CreditRequestEntity? = null
)