package com.food.payments.mapper

import com.food.payments.dto.PaymentDto
import com.food.payments.model.Payment
import org.springframework.stereotype.Component

@Component
class PaymentDtoMapper: Mapper<Payment, PaymentDto> {

    override fun map(type: Payment): PaymentDto {
        return PaymentDto(
            id = type.id,
            value = type.paymentValue,
            name = type.name,
            number = type.number,
            expirationAt = type.expirationAt,
            securityCode = type.securityCode,
            status = type.status,
            idOrder = type.idOrder,
            paymentType = type.paymentType
        )
    }
}