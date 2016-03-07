package com.taskworld.kxandroid.gson

import com.google.gson.Gson
import com.google.gson.JsonPrimitive
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import java.io.Reader

/**
 * Created by Kittinun Vantasin on 6/19/15.
 */

fun Number.toJson(): JsonPrimitive = JsonPrimitive(this)
fun Char.toJson(): JsonPrimitive = JsonPrimitive(this)
fun Boolean.toJson(): JsonPrimitive = JsonPrimitive(this)
fun String.toJson(): JsonPrimitive = JsonPrimitive(this)

//toJson
inline fun <reified T> T.toJson(gson: Gson, writer: JsonWriter) = gson.toJson(this, typeToken<T>(), writer)
inline fun <reified T> T.toJson(gson: Gson) = gson.toJson(this, typeToken<T>())

//fromJson
inline fun <reified T> Gson.fromJson(json: JsonReader) = this.fromJson<T>(json, typeToken<T>())
inline fun <reified T> Gson.fromJson(json: String) = this.fromJson<T>(json, typeToken<T>())
inline fun <reified T> Gson.fromJson(json: Reader) = this.fromJson<T>(json, typeToken<T>())