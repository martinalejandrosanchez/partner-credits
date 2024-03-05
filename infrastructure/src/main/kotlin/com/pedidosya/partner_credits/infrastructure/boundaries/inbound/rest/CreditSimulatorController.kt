package com.pedidosya.partner_credits.infrastructure.boundaries.inbound.rest

import com.pedidosya.partner_credits.application.loans.CreditConfirmation
import com.pedidosya.partner_credits.application.loans.CreditSimulator
import com.pedidosya.partner_credits.application.kyc.DataValidation
import com.pedidosya.partner_credits.domain.model.CreditDetails
import com.pedidosya.partner_credits.domain.model.CreditRequest
import com.pedidosya.partner_credits.domain.model.Currency
import com.pedidosya.partner_credits.infrastructure.boundaries.inbound.rest.dto.DataValidationDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal

@RestController
@RequestMapping("/credits")
class CreditSimulatorController(
    private val creditSimulator: CreditSimulator,
    private val creditConfirmation: CreditConfirmation,
    private val dataValidation: DataValidation
) {

    @GetMapping("/simulate")
    fun simulate(
        @RequestParam currency: Currency,
        @RequestParam amount: BigDecimal,
        @RequestParam installments: Int
    ): CreditDetails {
        return creditSimulator.simulate(currency, amount, installments)
    }

    @PostMapping("/validate-data")
    fun validate(
        @RequestBody dto: DataValidationDto
    ): Boolean {
        return dataValidation.validate(
            mail = dto.mail,
            phone = dto.phone,
            contract = dto.contract
        )
    }

    @PostMapping("/confirm")
    fun confirm(
        @RequestBody creditRequest: CreditRequest
    ) {
        creditConfirmation.confirmation(creditRequest)
    }
}