package com.taskworld.kxandroid.extension

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

/**
 * Created by Kittinun Vantasin on 10/17/14.
 */

val Activity.rootView: ViewGroup
    get() = window.decorView.findViewById(android.R.id.content) as ViewGroup

fun <T : View> Activity.bindView(id: Int): T {
    val view = findViewById(id) ?:
            throw IllegalArgumentException("Given ID $id could not be found in $this!")
    @Suppress("unchecked_cast")
    return view as T
}

fun <T : View> Activity.bindOptionalView(id: Int): T? {
    val view = findViewById(id)
    @Suppress("unchecked_cast")
    return view as? T
}

inline fun <reified T : Activity> Activity.startActivityForResult(requestCode: Int, vararg params: Pair<String, Any>) {
    startActivityForResult(createIntent<T>(params.toList()), requestCode)
}

fun Activity.toast(text: String?) {
    if (text == null) return
    Toast.makeText(this, text, Toast.LENGTH_LONG).show()
}

fun Activity.inflate(resourceId: Int, root: ViewGroup?, attachToRoot: Boolean): View {
    return LayoutInflater.from(this).inflate(resourceId, root, attachToRoot)
}

fun Activity.inflate(resourceId: Int, root: ViewGroup?): View {
    return LayoutInflater.from(this).inflate(resourceId, root)
}

inline fun <reified T : Activity> Activity.createIntentTo(): Intent {
    return Intent(this, T::class.java)
}

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
