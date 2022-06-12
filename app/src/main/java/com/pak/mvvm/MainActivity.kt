package com.pak.mvvm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.pak.mvvm.databinding.ActivityMainBinding
import com.pak.mvvm.db.ContactDB
import com.pak.mvvm.models.Contact
import com.pak.mvvm.network.ApiService
import com.pak.mvvm.network.RetrofitHelper
import com.pak.mvvm.respository.ContactRepository
import com.pak.mvvm.respository.ContactRepositoryImpl
import com.pak.mvvm.utils.LoggerService
import com.pak.mvvm.utils.MainViewModelFactory
import com.pak.mvvm.utils.Response
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var mainViewModel: MainViewModel
    private val url ="https://source.unsplash.com/user/c_v_r/100x100"

    lateinit var database : ContactDB

    // Injection
    @Inject
    lateinit var loggerService: LoggerService

    @Inject
    lateinit var contactRepository: ContactRepositoryImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        // Default way to create instance of view model
        // mainViewModel  = ViewModelProvider(this).get(MainViewModel::class.java)
        // For providing parameters to view model, its instance is intialised below
        //val phoneContactAPI = RetrofitHelper.getInstance().create(ApiService::class.java)
        //val contactRepository = ContactRepositoryImpl(phoneContactAPI)
        mainViewModel = ViewModelProvider(this, MainViewModelFactory(contactRepository)).get(MainViewModel::class.java)
        mainViewModel.contacts.observe(this, Observer {
            when(it){
                is Response.Loading -> {
                    // Show Loading progress
                }
                is Response.Success -> {
                    it.data?.let {
                       it.forEach { item ->
                            loggerService.log("Name :: " , item.name)
                        }
                    }

                }
                is Response.Failure -> {}
            }


        })
        binding.mainViewModel = mainViewModel
        binding.lifecycleOwner = this
        mainViewModel.updateMsg()
        val user = User("Shahid", "Nasrullah",url)
        binding.user = user

        // Getting Singleton database connection
        database = ContactDB.getDatabase(this)

        GlobalScope.launch {
            database.contactDao().insertContact(Contact(0, "Shakeel", "03226572725"))

        }

    }
}