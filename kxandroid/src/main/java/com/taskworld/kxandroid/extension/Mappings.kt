package com.taskworld.kxandroid.extension

/**
 * Created by Johnny Dew on 9/4/2015 AD.
 */

/**
 * Returns a list containing results of Pair<Key, Value> ordered by key sequence in keyList.
 */
fun <K, V> Map<K, V>.orderedList(vararg keyList: K): List<Pair<K, V>> {
    val result = keyList.fold(arrayListOf<Pair<K, V>>()) { list, key ->
        val value = get(key)
        if (value != null) {
            list.add(key to value)
        }
        list
    }
    return result
}

