package com.taskworld.kxandroid.support.v4

import android.app.Activity
import android.support.v4.app.ActivityOptionsCompat
import android.view.View
import android.support.v4.util.Pair as PairV4

fun Activity.kx_makeSceneTransitionAnimation(vararg sharedElement: View) =
        kx_makeSceneTransitionAnimation(*sharedElement.map { it to if (KxVersion.LOLLIPOP) it.transitionName else "" }.toTypedArray())

fun Activity.kx_makeSceneTransitionAnimation(vararg sharedElements: Pair<View, String>) =
        ActivityOptionsCompat.makeSceneTransitionAnimation(this, *sharedElements.map { PairV4.create(it.first, it.second) }.toTypedArray())
