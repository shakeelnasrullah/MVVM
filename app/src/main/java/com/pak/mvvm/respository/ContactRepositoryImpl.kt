package com.pak.mvvm.respository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pak.mvvm.models.PhoneContact
import com.pak.mvvm.network.ApiService
import com.pak.mvvm.utils.Response
import javax.inject.Inject


interface ContactRepository {
    suspend fun getPhoneContacts()
}


class ContactRepositoryImpl @Inject constructor(private val apiService: ApiService) : ContactRepository {

    private val phoneContacts = MutableLiveData<Response<PhoneContact>>()

    val contacts : LiveData<Response<PhoneContact>>
        get() = phoneContacts

    override suspend fun getPhoneContacts() {
        val result = apiService.getContacts()
        if (result?.body() != null) {
            phoneContacts.postValue(Response.Success(result.body()))
        }else
            phoneContacts.postValue(Response.Failure(result.message()))
    }
}