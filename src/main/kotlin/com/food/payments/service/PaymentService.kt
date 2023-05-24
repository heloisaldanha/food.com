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
        val payment = paymentMapper.map(paymentDto)
        Status.CREATED
        repository.save(payment)

        return paymentDtoMapper.map(payment)
    }

    fun update(id: Long, paymentDto: PaymentDto): PaymentDto {
        val payment = paymentMapper.map(paymentDto)
        payment.id
        repository.save(payment)
        return paymentDtoMapper.map(payment)
    }

    fun delete(id: Long) {
        repository.deleteById(id)
    }
}