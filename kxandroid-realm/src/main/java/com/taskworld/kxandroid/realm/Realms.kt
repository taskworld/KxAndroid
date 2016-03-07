package com.taskworld.kxandroid.realm

import io.realm.Realm
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.RealmResults
import io.realm.Sort

/**
 * Created by Johnny Dew on 6/8/2015 AD.
 */

/**
 * Utility Function helps update data in Realm.
 */

fun <T : Any> Realm.writeTransaction(init: Realm.() -> T): T {
    var t: T? = null

    executeTransaction {
        t = init(it)
    }

    return t!!
}

inline fun <reified T : RealmObject> Realm.createOrUpdate(id: String? = null, noinline init: T.() -> Unit) {
    val obj = if (id != null) findById<T>(id) else create<T>()
    obj.init()
}

inline fun <reified T : RealmObject> Realm.getLastUpdatedObject() = where(T::class.java).findAllSorted("updatedDate", Sort.DESCENDING).firstOrNull()
inline fun <reified T : RealmObject> Realm.getMostOutdatedObject() = where(T::class.java).findAllSorted("updatedDate", Sort.ASCENDING).firstOrNull()

inline fun <reified T : RealmObject> Realm.create() = createObject(T::class.java)

inline fun <reified T : RealmObject> Realm.from() = where(T::class.java)

inline fun <reified T : RealmObject> Realm.all() = allObjects(T::class.java)

inline fun <reified T : RealmObject> Realm.clearAll() = clear(T::class.java)

inline fun <reified T : RealmObject> Realm.findById(id: String) = from<T>().equalTo("id", id).findFirst()

inline fun <reified T : RealmObject> Realm.findOrCreate(id: String, realmIdHandler: (T, String) -> Unit): T {
    var model = findById<T>(id)
    if (model == null) {
        model = create()
        realmIdHandler(model!!, id)
    }
    return model
}

inline fun <reified T : RealmObject> Realm.findWithIds(vararg ids: String): List<T> = findWithIds(ids.toList())

inline fun <reified T : RealmObject> Realm.findWithIds(ids: List<String>): List<T> {
    val query = from<T>()
    ids.forEachIndexed { index, id ->
        if (index != 0) {
            query.or().equalTo("id", id)
        } else {
            query.equalTo("id", id)
        }
    }
    return query.findAll().toList()
}

inline fun <reified T : RealmObject> Realm.count() = all<T>().size

fun <T : RealmObject> RealmResults<T>.onChange(f: () -> Unit): RealmResults<T> {
    this.addChangeListener {
        f()
        this.removeChangeListeners()
    }
    return this
}

@Deprecated("mergeWith is no longer in use right now", ReplaceWith(""))
fun <T : RealmObject> RealmList<T>.mergeWith(list: List<T>, predicate: (T, T) -> Boolean) {
    //update/ add
    list.forEachIndexed { index, newItem ->
        val existedIndex = indexOfFirst {
            predicate(it, newItem)
        }

        if (existedIndex != -1) {
            removeAt(existedIndex)
            add(existedIndex, newItem)
        } else {
            add(newItem)
        }
    }

    //remove old item in db
    forEachIndexed { index, item ->
        val existedIndex = list.indexOfFirst {
            predicate(it, item)
        }

        if (existedIndex == -1) {
            removeAt(index)
        }
    }
}