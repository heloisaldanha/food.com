package com.food.payments.dto

import com.food.payments.enum.Status

data class PaymentDto(
    var id: Long,
    val value: Double,
    val name: String,
    val number: String,
    val expirationAt: String,
    val securityCode: String,
    var status: Status?,
    val idOrder: Long,
    val paymentType: Long
)
