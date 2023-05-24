package com.food.payments.mapper

interface Mapper<T, U> {

    fun map(type: T): U
}