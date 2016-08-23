package com.taskworld.kxandroid.support.v4

import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentTransaction

fun FragmentActivity.kx_finishAfterTransition() {
    if (KxVersion.LOLLIPOP) {
        finishAfterTransition()
    } else {
        finish()
    }
}

fun FragmentActivity.fragmentTransaction(transactionBlock: (FragmentTransaction).() -> (Unit)) {
    val transaction = supportFragmentManager.beginTransaction()
    transaction.transactionBlock()
    transaction.commit()
}