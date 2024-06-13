package com.minaMikhail.network.utils

import com.minaMikhail.network.annotations.SupportCache
import kotlin.reflect.KSuspendFunction1
import kotlin.reflect.KSuspendFunction2
import kotlin.reflect.KSuspendFunction3
import kotlin.reflect.KSuspendFunction4

fun KSuspendFunction1<*, *>.isSupportCache(): Boolean {
    this.annotations.find {
        it.annotationClass.java == SupportCache::class.java
    }?.let {
        return true
    } ?: run {
        return false
    }
}

@JvmName("KSuspendFunction2Cache")
fun KSuspendFunction2<*, *, *>.isSupportCache(): Boolean {
    this.annotations.find {
        it.annotationClass.java == SupportCache::class.java
    }?.let {
        return true
    } ?: run {
        return false
    }
}

@JvmName("KSuspendFunction3Cache")
fun KSuspendFunction3<*, *, *, *>.isSupportCache(): Boolean {
    this.annotations.find {
        it.annotationClass.java == SupportCache::class.java
    }?.let {
        return true
    } ?: run {
        return false
    }
}

@JvmName("KSuspendFunction4Cache")
fun KSuspendFunction4<*, *, *, *, *>.isSupportCache(): Boolean {
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

@JvmName("KSuspendFunction2Duration")
fun KSuspendFunction2<*, *, *>.getCacheDuration(): Long {
    this.annotations.find {
        it.annotationClass.java == SupportCache::class.java
    }?.let {
        return (it as SupportCache).cacheDurationSeconds
    } ?: run {
        return 0L
    }
}

@JvmName("KSuspendFunction3Duration")
fun KSuspendFunction3<*, *, *, *>.getCacheDuration(): Long {
    this.annotations.find {
        it.annotationClass.java == SupportCache::class.java
    }?.let {
        return (it as SupportCache).cacheDurationSeconds
    } ?: run {
        return 0L
    }
}

@JvmName("KSuspendFunction4Duration")
fun KSuspendFunction4<*, *, *, *, *>.getCacheDuration(): Long {
    this.annotations.find {
        it.annotationClass.java == SupportCache::class.java
    }?.let {
        return (it as SupportCache).cacheDurationSeconds
    } ?: run {
        return 0L
    }
}