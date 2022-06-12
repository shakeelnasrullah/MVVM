package com.pak.mvvm.utils


sealed class Response<T>(val data: T? = null, val errorMessage: String? = null) {

    class Loading<T> : Response<T>()
    class Success<T>(phoneContact: T? = null) : Response<T>(data = phoneContact)
    class Failure<T>(msg: String?) : Response<T>(errorMessage = msg)
}
