package com.food.payments.mapper

import com.food.payments.dto.PaymentDto
import com.food.payments.enum.Status
import com.food.payments.model.Payment
import org.springframework.stereotype.Component

@Component
class PaymentMapper: Mapper<PaymentDto, Payment> {

    override fun map(type: PaymentDto): Payment {
        return Payment(
            id = type.id,
            paymentValue = type.value,
            name = type.name,
            number = type.number,
            expirationAt = type.expirationAt,
            securityCode = type.securityCode,
            status = type.status!!,
            idOrder = type.idOrder,
            paymentType = type.paymentType
        )
    }
}