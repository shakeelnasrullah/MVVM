package com.pak.mvvm

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.pak.mvvm.databinding.ActivityMainBinding
import com.pak.mvvm.db.ContactDB
import com.pak.mvvm.models.Contact
import com.pak.mvvm.network.ApiService
import com.pak.mvvm.network.RetrofitHelper
import com.pak.mvvm.respository.ContactRepositoryImpl
import com.pak.mvvm.utils.MainViewModelFactory
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class DummyActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var mainViewModel: MainViewModel
    private val url = "https://source.unsplash.com/user/c_v_r/100x100"

    lateinit var database: ContactDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        // Default way to create instance of view model
        // mainViewModel  = ViewModelProvider(this).get(MainViewModel::class.java)
        // For providing parameters to view model, its instance is intialized below
        val phoneContactAPI = RetrofitHelper.getInstance().create(ApiService::class.java)
        val contactRepository = ContactRepositoryImpl(phoneContactAPI)
        mainViewModel = ViewModelProvider(
            this,
            MainViewModelFactory(contactRepository)
        ).get(MainViewModel::class.java)
        /*mainViewModel.msgText.observe(this) {
            binding.secondTextTv.text = it
        }*/
        mainViewModel.contacts.observe(this, Observer {
            it.data?.forEach { item ->
                Log.d("Name :: ", item.name)
            }

        })
        binding.mainViewModel = mainViewModel
        binding.lifecycleOwner = this
        mainViewModel.updateMsg()
        val user = User("Shahid", "Nasrullah", url)
        binding.user = user


        /*GlobalScope.launch {
            val result = phoneContactAPI.getContacts()
            val response = result.body()
            response!!.forEach {
                Log.d("Name :: " , it.name)
            }
        }*/
        /* val handler : Handler = Handler()
         handler.postDelayed(Runnable {
             binding.msg = "Hi Shakeel !\nHow are you?"
         }, 5000)*/

        // Creation and initialization of Database
        //database = Room.databaseBuilder(applicationContext, ContactDB::class.java, "contactDB").build()

        // Getting Singleton database connection
        database = ContactDB.getDatabase(this)

        GlobalScope.launch {
            database.contactDao().insertContact(Contact(0, "Shakeel", "03226572725"))

        }

    }
}
