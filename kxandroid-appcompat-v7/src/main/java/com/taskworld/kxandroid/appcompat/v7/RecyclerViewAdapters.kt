package com.taskworld.kxandroid.appcompat.v7

import android.support.v7.widget.RecyclerView

/**
 * Created by Johnny Dew on 6/30/2015 AD.
 */

fun <VH : RecyclerView.ViewHolder, E> RecyclerView.Adapter<VH>.modifyDataSet(list: List<E>, modifyBuilder: List<E>.() -> Unit) {
    list.modifyBuilder()
    notifyDataSetChanged()
}
