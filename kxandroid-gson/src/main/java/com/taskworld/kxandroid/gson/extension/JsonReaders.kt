package com.taskworld.android.core.extension

import com.google.gson.stream.JsonReader

/**
 * Created by Johnny Dew on 6/18/2015 AD.
 */

fun <T> JsonReader.openObject(handler: (reader: JsonReader) -> T): T {
    beginObject()
    val t = handler(this)
    endObject()
    return t
}

fun <T> JsonReader.openArray(handler: (reader: JsonReader) -> T): T {
    beginArray()
    val t = handler(this)
    endArray()
    return t
}

/*
    Looking for JsonObject specific by `key` inside current JsonObject.
 */
fun JsonReader.findJsonEntity(key: String, handler: (reader: JsonReader) -> Unit) {
    while (hasNext()) {
        val name = nextName()
        if (name.equals(key)) {
            handler(this)
        } else {
            skipValue()
        }
    }
}

fun JsonReader.readJsonObject(handler: (reader: JsonReader) -> Unit) {
    while (hasNext()) {
        handler(this)
    }
}

fun JsonReader.readJsonArray(handler: (reader: JsonReader) -> Unit) {
    while (hasNext()) {
        handler(this)
    }
}
