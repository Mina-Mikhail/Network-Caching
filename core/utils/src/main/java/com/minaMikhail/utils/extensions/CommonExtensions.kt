package com.minaMikhail.utils.extensions

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

fun Any?.toJsonString(): String? {
    return if (this == null) {
        null
    } else {
        Gson().toJson(this)
    }
}

fun <T : Any> String?.toJsonModel(modelClass: Class<T>): T? {
    return if (this.isNullOrEmpty()) {
        null
    } else {
        Gson().fromJson(this, modelClass)
    }
}


inline fun <reified T> String?.toJsonList(): T? {
    return if (this.isNullOrEmpty()) {
        null
    } else {
        Gson().fromJson(this, object : TypeToken<T>() {}.type)
    }
}