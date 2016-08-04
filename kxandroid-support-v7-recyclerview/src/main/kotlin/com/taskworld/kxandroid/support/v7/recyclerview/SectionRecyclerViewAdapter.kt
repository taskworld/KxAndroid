package com.taskworld.kxandroid.support.v7.recyclerview

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import kotlin.properties.Delegates

val SECTION_HEADER_VIEW_TYPE = 1000
val SECTION_ITEM_VIEW_TYPE = 1001

interface SectionModelType<T> {
    val items: List<T>

    operator fun get(index: Int): T = items[index]

    fun size(): Int = items.size
}

class SimpleSection<T>(val name: String, override var items: List<T>) : SectionModelType<T>

fun <T, X> List<T>.mapToSection(sectionName: (T) -> X): List<SimpleSection<T>> =
        groupBy { sectionName(it) }.mapTo(mutableListOf()) {
            SimpleSection(it.key.toString(), it.value)
        }

abstract class SectionRecyclerViewAdapter<T, S : SectionModelType<T>, VH : android.support.v7.widget.RecyclerView.ViewHolder> : android.support.v7.widget.RecyclerView.Adapter<VH>() {
    var sections: List<S> by Delegates.observable(listOf()) { property, oldValue, newValue ->
        headerPositions = newValue.foldIndexed(mutableListOf()) { index, acc, section ->
            val value = if (index == 0) 0 else {
                val sizeOfPreviousSection = sections[index - 1].size()
                val previousIndex = acc[index - 1]
                val self = 1
                previousIndex + sizeOfPreviousSection + self
            }
            acc.add(value)
            acc
        }
    }

    private var headerPositions = mutableListOf<Int>()

    abstract var createViewHolder: (ViewGroup?, Int) -> VH
    abstract var bindHeaderViewHolder: (VH, Int, S) -> Unit
    abstract var bindItemViewHolder: (VH, Int, T) -> Unit

    override fun getItemCount(): Int = sections.fold(sections.size) { acc, section -> acc + section.items.size }

    override fun getItemViewType(position: Int): Int = if (headerPositions.contains(position)) SECTION_HEADER_VIEW_TYPE else SECTION_ITEM_VIEW_TYPE

    override fun onBindViewHolder(holder: VH, position: Int) {
        if (headerPositions.contains(position)) {
            bindHeaderViewHolder(holder, position, sections[headerPositions.indexOf(position)])
        } else {
            val item = findItemFromPosition(position)
            bindItemViewHolder(holder, position, item!!)
        }
    }

    private fun findItemFromPosition(position: Int): T? {
        //copy into another list
        val _headerPositions = headerPositions.toMutableList()

        //add upperbound to support last item
        _headerPositions.add(Int.MAX_VALUE)

        for (index in 0.._headerPositions.size - 1) {
            val headerPosition = _headerPositions[index]
            if (headerPosition > position) {
                val previousIndex = index - 1
                val previousHeaderPosition = _headerPositions[previousIndex]
                val relativePreviousIndex = position - previousHeaderPosition - 1
                return sections[previousIndex][relativePreviousIndex]
            }
        }

        return null
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): VH = createViewHolder.invoke(parent, viewType)
}