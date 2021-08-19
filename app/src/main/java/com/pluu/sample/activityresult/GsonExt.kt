package com.pluu.sample.activityresult

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

fun <T> T.toJson(): String {
  return Gson().toJson(this)
}

inline fun <reified T> String.fromJson(): T? {
  return Gson().fromJson(this, object : TypeToken<T>() {}.type)
}
