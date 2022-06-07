package com.pak.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pak.mvvm.models.PhoneContact
import com.pak.mvvm.respository.ContactRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repositoryImpl: ContactRepositoryImpl) : ViewModel() {


    init {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryImpl.getPhoneContacts()
        }
    }

    val contacts : LiveData<PhoneContact>
    get() = repositoryImpl.contacts

    val msgText = MutableLiveData("Hello Shakeel")

    fun updateMsg() {
        msgText.postValue("Maa Tuje Salam")
    }

}