package com.taskworld.kxandroid.extension

import android.support.v7.widget.AppCompatSpinner
import android.view.View
import android.widget.AdapterView

/**
 * Created by tipatai on 1/22/16 AD.
 */


fun AppCompatSpinner.onSelectedChangedListener(init: _AppCompatSpinner_OnSelectedListener.() -> Unit) {
    val listener = _AppCompatSpinner_OnSelectedListener()
    listener.init()
    onItemSelectedListener = listener
}

class _AppCompatSpinner_OnSelectedListener : AdapterView.OnItemSelectedListener {

    private var _nothingSelected: ((AdapterView<*>?) -> Unit)? = null
    private var _itemSelected: ((AdapterView<*>?, View?, Int, Long) -> Unit)? = null


    override fun onNothingSelected(parent: AdapterView<*>?) {
        _nothingSelected?.invoke(parent)
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        _itemSelected?.invoke(parent, view, position, id)
    }

    fun nothingSelected(listener: ((AdapterView<*>?) -> Unit)?) {
        _nothingSelected = listener
    }

    fun itemSelected(listener: ((AdapterView<*>?, View?, Int, Long) -> Unit)?) {
        _itemSelected = listener
    }

}