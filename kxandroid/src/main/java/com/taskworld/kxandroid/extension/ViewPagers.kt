package com.taskworld.kxandroid.extension

import android.support.v4.view.ViewPager

/**
 * Created by Kittinun Vantasin on 4/22/15.
 */

fun ViewPager.onPageChangeListener(init: _ViewPager_OnPageChangeListener.() -> Unit) {
    val listener = _ViewPager_OnPageChangeListener()
    listener.init()
    addOnPageChangeListener(listener)
}

//private stuffs
class _ViewPager_OnPageChangeListener : ViewPager.OnPageChangeListener {

    private var onPageScrolled: ((Int, Float, Int) -> Unit)? = null
    private var onPageSelected: ((Int) -> Unit)? = null
    private var onPageScrollStateChanged: ((Int) -> Unit)? = null

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        onPageScrolled?.invoke(position, positionOffset, positionOffsetPixels)
    }

    //proxy method
    fun onPageScrolled(listener: (Int, Float, Int) -> Unit) {
        onPageScrolled = listener
    }

    override fun onPageSelected(position: Int) {
        onPageSelected?.invoke(position)
    }

    //proxy method
    fun onPageSelected(listener: (Int) -> Unit) {
        onPageSelected = listener
    }

    override fun onPageScrollStateChanged(state: Int) {
        onPageScrollStateChanged?.invoke(state)
    }

    fun onPageScrollStateChanged(listener: (Int) -> Unit) {
        onPageScrollStateChanged = listener
    }

}