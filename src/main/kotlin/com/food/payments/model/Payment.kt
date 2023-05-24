package com.food.payments.model

import com.food.payments.enum.Status
import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.Size

@Entity
@Table(name = "payments")
data class Payment(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @NotNull
    @Positive
    val value: Int,

    @NotBlank
    @Size(max = 100)
    val name: String,

    @NotBlank
    @Size(max = 19)
    val number: String,

    @NotBlank
    @Size(max = 7)
    val expirationAt: String,

    @NotBlank
    @Size(min = 3, max = 3)
    val securityCode: String,

    @NotNull
    @Enumerated(EnumType.STRING)
    val status: Status,

    @NotNull
    val idOrder: Long,

    @NotNull
    val paymentType: Long
)