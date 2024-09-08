package com.example.atlys.helper

import com.example.atlys.BaseApplication
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

inline fun <reified R, reified T> R.toDomainLayer(): T {
    val gson = BaseApplication.gsonProvider.getGson()
    val stringData = gson.toJson(this)
    val type: Type = object : TypeToken<T>() {}.type
    return gson.fromJson(stringData, type)
}