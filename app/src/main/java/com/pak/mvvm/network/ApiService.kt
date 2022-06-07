package com.pak.mvvm.network

import com.pak.mvvm.models.PhoneContact
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {


    @GET("/imkhan334/demo-1/call")
    suspend fun getContacts() : Response<PhoneContact>
}