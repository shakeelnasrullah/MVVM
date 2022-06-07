package com.pak.mvvm.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pak.mvvm.MainViewModel
import com.pak.mvvm.respository.ContactRepositoryImpl

// Factory is created for passing value to view model
class MainViewModelFactory(private val repositoryImpl: ContactRepositoryImpl ) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repositoryImpl) as T
    }
}