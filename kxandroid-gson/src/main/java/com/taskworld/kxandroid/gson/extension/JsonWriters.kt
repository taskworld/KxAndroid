package com.taskworld.android.core.extension

import com.google.gson.stream.JsonWriter
import com.taskworld.android.core.rest.serializer.TWGsonAdapter
import java.util.Date

/**
 * Created by Prisa Dumrongsiri on 9/4/2015 AD.
 */

fun JsonWriter.writeProperties(properties: List<Pair<String, Any?>>): Unit {
    properties.forEach { pair ->
        val (key, value) = pair
        when (value) {
            is Boolean -> name(key).value(value)
            is Int -> name(key).value(value)
            is Float -> name(key).value(value)
            is Long -> name(key).value(value)
            is Double -> name(key).value(value)
            is Number -> name(key).value(value)
            is String -> name(key).value(value)
            is Date -> {
                name(key)
                TWGsonAdapter.dateWriter(this, value)
            }
            else -> name(key).nullValue()
        }
    }
}

fun JsonWriter.writeObject(build: JsonWriter.() -> Unit) {
    beginObject()
    build()
    endObject()
}

fun JsonWriter.writeArray(build: JsonWriter.() -> Unit) {
    beginArray()
    build()
    endArray()
}

fun JsonWriter.name(key: String, build: JsonWriter.() -> Unit) {
    name(key)
    build()
}
