package com.taskworld.kxandroid.support.v7.recyclerview

import android.support.v7.widget.RecyclerView

fun <VH : RecyclerView.ViewHolder, E> RecyclerView.Adapter<VH>.modifyDataSet(list: List<E>, modifyBuilder: List<E>.() -> Unit) {
    list.modifyBuilder()
    notifyDataSetChanged()
}
