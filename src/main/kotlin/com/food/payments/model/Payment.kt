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
    var paymentValue: Double,

    @NotBlank
    @Size(max = 100)
    var name: String,

    @NotBlank
    @Size(max = 19)
    var number: String,

    @NotBlank
    @Size(max = 7)
    var expirationAt: String,

    @NotBlank
    @Size(min = 3, max = 3)
    var securityCode: String,

    @NotNull
    @Enumerated(EnumType.STRING)
    var status: Status,

    @NotNull
    var idOrder: Long,

    @NotNull
    var paymentType: Long
)