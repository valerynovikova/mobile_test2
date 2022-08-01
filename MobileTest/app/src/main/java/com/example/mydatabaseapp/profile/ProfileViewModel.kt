package com.example.mydatabaseapp.profile

import android.app.Application
import android.util.Log
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.*
import com.example.mydatabaseapp.database.LoginEntity
import com.example.mydatabaseapp.database.LoginRepository
import kotlinx.coroutines.*


class ProfileViewModel(private val repository: LoginRepository, application: Application) :
    AndroidViewModel(application), Observable {

    init {
        Log.i("MYTAG", "init")
    }


    private var userdata: String? = null

    var userDetailsLiveData = MutableLiveData<Array<LoginEntity>>()

    @Bindable
    val inputEmail = MutableLiveData<String>()

    @Bindable
    val inputPassword = MutableLiveData<String>()

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)


    private val _navigateto = MutableLiveData<Boolean>()

    val navigateto: LiveData<Boolean>
        get() = _navigateto

    private val _errorToast = MutableLiveData<Boolean>()

    val errotoast: LiveData<Boolean>
        get() = _errorToast

    private val _errorToastEmail = MutableLiveData<Boolean>()

    val errotoastEmail: LiveData<Boolean>
        get() = _errorToastEmail


    fun sumbitButton() {
        Log.i("MYTAG", "Inside SUBMIT BUTTON")
        if (inputEmail.value == null || inputPassword.value == null) {
            _errorToast.value = true
        } else {
            uiScope.launch {
//            withContext(Dispatchers.IO) {
                val usersEmails = repository.getEmail(inputEmail.value!!)
                Log.i("MYTAG", usersEmails.toString() + "------------------")
                if (usersEmails != null) {
                    _errorToastEmail.value = true
                    Log.i("MYTAG", "Inside if Not null")
                } else {
                    Log.i("MYTAG", userDetailsLiveData.value.toString() + "ASDFASDFASDFASDF")
                    Log.i("MYTAG", "OK im in")
                    val email = inputEmail.value!!
                    val password = inputPassword.value!!
                    Log.i("MYTAG", "insidi Sumbit")
                    insert(LoginEntity(0, email, password))
                    inputEmail.value = null
                    inputPassword.value = null
                    _navigateto.value = true
                }
            }
        }
    }


    override fun onCleared() {
        super.onCleared()
    }

    fun doneNavigating() {
        _navigateto.value = false
        Log.i("MYTAG", "Done navigating ")
    }

    fun donetoast() {
        _errorToast.value = false
        Log.i("MYTAG", "Done taoasting ")
    }

    fun donetoastEmail() {
        _errorToast.value = false
        Log.i("MYTAG", "Done taoasting  email")
    }

    private fun insert(user: RegisterEntity): Job = viewModelScope.launch {
        repository.insert(user)
    }

//    fun clearALl():Job = viewModelScope.launch {
//        repository.deleteAll()
//    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

}





