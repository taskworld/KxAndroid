package com.taskworld.kxandroid.realm

import com.taskworld.kxandroid.realm.model.ObjectId

/**
 * Created by Kittinun Vantasin on 7/2/15.
 */

fun ObjectId.getId() = ObjectId().toHexString()
