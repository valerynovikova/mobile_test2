package com.example.mydatabaseapp.database

import android.util.Log
import androidx.lifecycle.LiveData

class LoginRepository(private val dao: LoginDatabaseDao) {

    val users = dao.getAllUsers()
    suspend fun insert(user: LoginEntity) {
        return dao.insert(user)
    }

    suspend fun getEmail(email: String):LoginEntity?{
        Log.i("MYTAG", "inside Repository Getusers fun ")
        return dao.getEmail(email)
    }

}