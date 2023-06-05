package com.food.payments.service

import com.food.payments.dto.PaymentDto
import com.food.payments.enum.Status
import com.food.payments.http.OrderClient
import com.food.payments.mapper.PaymentDtoMapper
import com.food.payments.mapper.PaymentMapper
import com.food.payments.model.Payment
import com.food.payments.repository.PaymentRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class PaymentService(
    private val repository: PaymentRepository,
    private val paymentDtoMapper: PaymentDtoMapper,
    private val paymentMapper: PaymentMapper,
    private val order: OrderClient
) {

    fun getAllPayments(page: Pageable): Page<PaymentDto> {
        val payments = repository.findAll(page)

        return payments.map { payment -> paymentDtoMapper.map(payment) }
    }

    fun getById(id: Long): PaymentDto {
        val paymentById = repository.findById(id).orElseThrow { NotFoundException() }

        return paymentDtoMapper.map(paymentById)
    }

    fun create(paymentDto: PaymentDto): PaymentDto {
        paymentDto.status = Status.CREATED
        val payment = paymentMapper.map(paymentDto)

        repository.save(payment)

        return paymentDtoMapper.map(payment)
    }

    fun update(id: Long, paymentDto: PaymentDto): PaymentDto {
        val paymentById = repository.findById(id).orElseThrow { Exception("Pagamento não encontrado.") }
        paymentById.paymentValue = paymentDto.value
        paymentById.name = paymentDto.name
        paymentById.number = paymentDto.number
        paymentById.expirationAt = paymentDto.expirationAt
        paymentById.securityCode = paymentDto.securityCode
        paymentById.idOrder = paymentDto.idOrder
        paymentById.paymentType = paymentDto.paymentType

        repository.save(paymentById)

        return paymentDtoMapper.map(paymentById)
    }

    fun delete(id: Long) {
        repository.findById(id).orElseThrow { Exception("Pagamento não encontrado.") }
        repository.deleteById(id)
    }

    fun paymentConfirmed(id: Long) {
        val payment: Optional<Payment> = repository.findById(id)
        if (!payment.isPresent) {
            throw EntityNotFoundException()
        }

        payment.get().status = Status.CONFIRMED
        repository.save(payment.get())
        order.putPayments(payment.get().idOrder)

    }

    fun updateStatus(id: Long) {
        val payment: Optional<Payment> = repository.findById(id)
        if (!payment.isPresent) {
            throw EntityNotFoundException()
        }

        payment.get().status = Status.CONFIRMED_WITHOUT_INTEGRATION
        repository.save(payment.get())
    }

}