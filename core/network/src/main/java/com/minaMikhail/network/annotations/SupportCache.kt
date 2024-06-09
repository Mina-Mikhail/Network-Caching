package com.minaMikhail.network.annotations

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class SupportCache(
    val cacheDurationSeconds: Long
)