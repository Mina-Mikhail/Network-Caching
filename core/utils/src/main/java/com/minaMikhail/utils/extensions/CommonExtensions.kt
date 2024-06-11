package com.minaMikhail.utils.extensions

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

fun Any.toJsonString(): String {
    return Gson().toJson(this)
}

fun <T> String.toJsonModel(modelClass: Class<T>): T? {
    return Gson().fromJson(this, modelClass)
}

inline fun <reified T> String.toJsonList(): T? {
    return Gson().fromJson(this, object : TypeToken<T>() {}.type)
}