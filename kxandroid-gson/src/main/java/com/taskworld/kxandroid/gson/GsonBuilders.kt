package com.taskworld.kxandroid.gson

import com.google.gson.GsonBuilder
import com.google.gson.InstanceCreator
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import com.google.gson.TypeAdapter
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import java.lang.reflect.Type

/**
 * Created by Kittinun Vantasin on 6/16/15.
 */

inline fun <reified T> typeToken(): Type = object : TypeToken<T>() {}.type

inline fun <reified T> GsonBuilder.registerTypeAdapter(adapter: Any) = registerTypeAdapter(typeToken<T>(), adapter)

fun GsonBuilder.registerTypeAdapter(className: String, adapter: Any) = registerTypeAdapter(Class.forName(className), adapter)

inline fun <reified T> GsonBuilder.registerReadAdapter(noinline reader: (JsonReader) -> T): GsonBuilder {
    val listener = _TypeAdapter<T>()
    listener.read = reader
    return registerTypeAdapter(typeToken<T>(), listener)
}

inline fun <reified T> GsonBuilder.registerWriteAdapter(noinline writer: (JsonWriter, T) -> Unit): GsonBuilder {
    val listener = _TypeAdapter<T>()
    listener.write = writer
    return registerTypeAdapter(typeToken<T>(), listener)
}

inline fun <reified T> GsonBuilder.registerAdapter(noinline reader: ((JsonReader) -> T)?, noinline writer: ((JsonWriter, T) -> Unit)?): GsonBuilder {
    val listener = _TypeAdapter<T>()
    listener.read = reader
    listener.write = writer
    return registerTypeAdapter(typeToken<T>(), listener)
}

inline fun <reified T : Any> GsonBuilder.registerAdapter(noinline serializer: ((src: T, typeOfSrc: Type, context: JsonSerializationContext) -> JsonElement)?,
                                                         noinline deserializer: ((json: JsonElement, typeOfT: Type, context: JsonDeserializationContext) -> T)?): GsonBuilder {
    if (serializer != null) serialize(serializer)
    if (deserializer != null) deserialize(deserializer)
    return this
}

fun <T> instanceCreator(creator: (type: Type) -> T) = InstanceCreator { creator(it) }

inline fun <reified T> GsonBuilder.serialize(noinline serializer: (src: T, type: Type, context: JsonSerializationContext) -> JsonElement) =
        registerTypeAdapter(typeToken<T>(), jsonSerializer(serializer))

inline fun <reified T : Any> GsonBuilder.deserialize(noinline deserializer: (json: JsonElement, type: Type, context: JsonDeserializationContext) -> T?) =
        registerTypeAdapter(typeToken<T>(), jsonDeserializer(deserializer))

inline fun <reified T> GsonBuilder.createInstances(noinline creator: (type: Type) -> T) = registerTypeAdapter(typeToken<T>(), instanceCreator(creator))

//private stuffs
inline fun <T> jsonSerializer(crossinline serializer: (src: T, type: Type, context: JsonSerializationContext) -> JsonElement): JsonSerializer<T> =
        JsonSerializer { src, type, context ->
            serializer(src, type, context)
        }

fun <T : Any> jsonDeserializer(deserializer: (json: JsonElement, type: Type, context: JsonDeserializationContext) -> T?): JsonDeserializer<T> =
        JsonDeserializer<T> { json, type, context ->
            deserializer(json, type, context)
        }

class _TypeAdapter<T> : TypeAdapter<T>() {

    var read: ((JsonReader) -> T)? = null
    var write: ((JsonWriter, T) -> Unit)? = null

    override fun read(reader: JsonReader?) = read?.invoke(reader!!)

    //proxy method
    fun read(listener: ((JsonReader) -> T)?) {
        read = listener
    }

    override fun write(writer: JsonWriter?, value: T) {
        write?.invoke(writer!!, value)
    }

    //proxy method
    fun write(listener: ((JsonWriter, T) -> Unit)?) {
        write = listener
    }

}
