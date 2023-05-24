package com.food.payments.dto

import com.food.payments.enum.Status

data class PaymentDto(
    val id: Long,
    val value: Int,
    val name: String,
    val number: String,
    val expirationAt: String,
    val securityCode: String,
    val status: Status,
    val idOrder: Long,
    val paymentType: Long
)
