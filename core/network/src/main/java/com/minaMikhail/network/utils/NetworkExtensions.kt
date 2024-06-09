package com.minaMikhail.network.utils

import com.minaMikhail.network.annotations.SupportCache
import kotlin.reflect.KSuspendFunction1

fun KSuspendFunction1<*, *>.isSupportCache(): Boolean {
    this.annotations.find {
        it.annotationClass.java == SupportCache::class.java
    }?.let {
        return true
    } ?: run {
        return false
    }
}

fun KSuspendFunction1<*, *>.getCacheDuration(): Long {
    this.annotations.find {
        it.annotationClass.java == SupportCache::class.java
    }?.let {
        return (it as SupportCache).cacheDurationSeconds
    } ?: run {
        return 0L
    }
}