package com.example.mydatabaseapp.profile

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mydatabaseapp.database.LoginRepository
import java.lang.IllegalArgumentException

class ProfileViewModelFactory(
    private  val repository: LoginRepository,
    private val application: Application):ViewModelProvider.Factory{
    @Suppress("Unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
       if(modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
           return ProfileViewModel(repository, application) as T
       }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}