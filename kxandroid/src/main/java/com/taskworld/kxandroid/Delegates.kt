package com.taskworld.kxandroid

import kotlin.properties.Delegates
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Created by Johnny Dew on 8/4/2015 AD.
 */

fun <T : Any> Delegates.lazyObservable(initializer: () -> T, onChange: (desc: KProperty<*>, oldValue: T?, newValue: T) -> Unit): ReadWriteProperty<Any?, T> {
    return LazyObservableProperty (initializer) { desc, old, new ->
        onChange(desc, old, new)
        true
    }
}

private class LazyObservableProperty<T : Any>(private val initializer: () -> T, private val onChange: (name: KProperty<*>, oldValue: T?, newValue: T) -> Boolean) : ReadWriteProperty<Any?, T> {

    private var value: T? = null

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        if (value == null) {
            value = initializer()
        }
        return value!!
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        if (onChange(property, this.value, value)) {
            this.value = value
        }
    }

}