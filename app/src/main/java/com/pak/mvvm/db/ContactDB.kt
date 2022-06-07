package com.pak.mvvm.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pak.mvvm.dao.ContactDao
import com.pak.mvvm.models.Contact


@Database(entities = [Contact::class], version = 1)
abstract class ContactDB : RoomDatabase() {

    abstract fun contactDao(): ContactDao

    companion object {
        @Volatile
        private var INSTANCE: ContactDB? = null

        fun getDatabase(context: Context): ContactDB {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        ContactDB::class.java,
                        "contactDB"
                    ).build()
                }
            }
            return INSTANCE!!
        }
    }

}