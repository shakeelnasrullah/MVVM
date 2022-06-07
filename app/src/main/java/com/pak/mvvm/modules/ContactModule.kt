package com.pak.mvvm.modules

import com.pak.mvvm.network.ApiService
import com.pak.mvvm.network.RetrofitHelper
import com.pak.mvvm.respository.ContactRepository
import com.pak.mvvm.respository.ContactRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

@InstallIn(SingletonComponent::class)
@Module
class ContactModule {

    @Provides
    fun provideContactRepository( apiService: ApiService) : ContactRepository {
        return ContactRepositoryImpl(apiService)
    }


}