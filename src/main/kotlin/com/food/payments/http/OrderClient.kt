package com.food.payments.http

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@FeignClient("order-ms")
interface OrderClient {

    @RequestMapping(method = [RequestMethod.PUT], value = ["/pedidos/{id}/pago"])
    fun putPayments(@PathVariable id: Long)
}