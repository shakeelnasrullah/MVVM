package com.pak.mvvm.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.pak.mvvm.models.Contact

@Dao
interface ContactDao {

    @Insert
    suspend fun insertContact(contact: Contact)

    @Delete
    suspend fun deleteContact(contact: Contact)

    @Update
    suspend fun updateContact(contact: Contact)

    @Query("SELECT * FROM contact")
    fun getContact() : LiveData<List<Contact>>
}