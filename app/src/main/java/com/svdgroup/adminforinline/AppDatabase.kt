package com.svdgroup.adminforinline

import com.google.firebase.database.FirebaseDatabase

object AppDatabase {
    private var mDatabase: FirebaseDatabase? = null

    fun getDatabase(): FirebaseDatabase? {
        if (mDatabase == null) {
            mDatabase = FirebaseDatabase.getInstance()
            mDatabase!!.setPersistenceEnabled(true)
        }
        mDatabase!!.reference.keepSynced(true)
        return mDatabase
    }
}