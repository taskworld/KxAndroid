package com.taskworld.kxandroid

import android.view.View
import android.view.ViewGroup

val ViewGroup.childIndices: IntRange
    get() = 0..childCount - 1

// Make ViewGroups compatible with for-loop clause
operator fun ViewGroup.iterator(): Iterator<View> {
    return object : ListIterator<View> {

        var cursor = 0

        override fun next(): View = getChildAt(cursor++)

        override fun hasPrevious(): Boolean = cursor > 0

        override fun previous(): View = getChildAt(cursor--)

        override fun nextIndex(): Int = cursor + 1

        override fun previousIndex(): Int = cursor - 1

        override fun hasNext(): Boolean = cursor < childCount - 1

    }
}

