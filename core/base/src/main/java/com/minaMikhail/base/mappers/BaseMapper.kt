package com.minaMikhail.base.mappers

interface BaseMapper<in From, out To> {
    fun map(from: From): To
}