package com.pedidosya.partner_credits.infrastructure.boundaries.outbound.postgres.entities

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToMany
import org.hibernate.annotations.CreationTimestamp
import java.math.BigDecimal
import java.time.Instant

@Entity(name = "CREDIT_REQUEST")
class CreditRequestEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "UUID", nullable = false)
    val uuid: String,

    @Column(name = "CURRENCY", nullable = false)
    val currency: String,

    @Column(name = "AMOUNT", nullable = false)
    val amount: BigDecimal,

    @Column(name = "TOTAL_AMOUNT", nullable = false)
    val totalAmount: BigDecimal,

    @Column(name = "INSTALLMENTS", nullable = false)
    val installments: Int,

    @Column(name = "MAIL", nullable = false)
    val mail: String,

    @OneToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    @JoinColumn(name = "credit_request_id")
    val installmentPlanDetails: List<InstallmentEntity>,

    @field:CreationTimestamp
    @Column(name = "created_at")
    var createdAt: Instant? = null
)