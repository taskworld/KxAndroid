package com.taskworld.kxandroid.support.design

import android.app.Activity
import android.content.Context
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.Snackbar
import android.view.View
import android.view.ViewGroup
import com.taskworld.kxandroid.rootView
import com.taskworld.kxandroid.iterator

/**
 * Created by tipatai on 3/7/16 AD.
 */

fun Activity.showSnackBar(text: CharSequence,
                          childInCoordinatorLayout: View? = null,
                          duration: Int = Snackbar.LENGTH_LONG,
                          action: Pair<CharSequence, ((View) -> Unit)>? = null) {

    var snackBar: Snackbar
    if (childInCoordinatorLayout == null) {
        // To enable swipe-to-dismiss feature, Snackbar must be contained in CoordinatorLayout.
        // This is kind of annoying and prone to bug if we have to add CoordinatorLayout
        // into every XML files we have. So, the way to go is to create dummy CoordinatorLayout only if
        // it not specified.
        var snackBarParentView = findSuitableSnackBarParent(this, rootView)
        snackBar = Snackbar.make(snackBarParentView, text, duration)
    } else {
        snackBar = Snackbar.make(childInCoordinatorLayout, text, duration)
    }

    snackBar.setAction(action?.component1(), action?.component2())
    snackBar.show()
}

private fun findSuitableSnackBarParent(context: Context, rootView: ViewGroup): ViewGroup {
    var parent: ViewGroup? = null
    // Before adding the new dummy CoordinatorLayout into current window,
    // We should find whether we had already added it or not
    for (v in rootView) {
        if (v.tag != null && v.tag.equals("dummy_coordinator")) {
            parent = v as ViewGroup
            break
        }
    }

    // If we can't find one, create it add into rootView
    if (parent == null) {
        parent = CoordinatorLayout(context)
        parent.tag = "dummy_coordinator"
        rootView.addView(parent)
    }

    return parent
}