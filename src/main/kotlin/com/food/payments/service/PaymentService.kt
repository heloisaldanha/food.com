package com.food.payments.service

import com.food.payments.dto.PaymentDto
import com.food.payments.enum.Status
import com.food.payments.mapper.PaymentDtoMapper
import com.food.payments.mapper.PaymentMapper
import com.food.payments.repository.PaymentRepository
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class PaymentService(
    private val repository: PaymentRepository,
    private val paymentDtoMapper: PaymentDtoMapper,
    private val paymentMapper: PaymentMapper
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
        var paymentById = repository.findById(id).orElseThrow { Exception("Pagamento não encontrado.") }
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
}