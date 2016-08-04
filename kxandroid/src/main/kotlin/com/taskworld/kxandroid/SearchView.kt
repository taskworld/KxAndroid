package com.taskworld.kxandroid

import android.widget.SearchView

fun SearchView.onQueryChangeListener(init: _SearchView_OnQueryChangeListener.() -> Unit) {
    val listener = _SearchView_OnQueryChangeListener()
    listener.init()
    setOnQueryTextListener(listener)
}

class _SearchView_OnQueryChangeListener : SearchView.OnQueryTextListener {

    private var onQueryChange: ((String) -> Boolean)? = null
    private var onQuerySubmit: ((String) -> Boolean)? = null

    override fun onQueryTextChange(newText: String): Boolean {
        return onQueryChange?.invoke(newText) ?: false
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        return onQuerySubmit?.invoke(query) ?: false
    }

    fun queryChange(listener: ((String) -> Boolean)?) {
        onQueryChange = listener
    }

    fun querySubmit(listener: ((String) -> Boolean)?) {
        onQuerySubmit = listener
    }

}