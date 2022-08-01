package com.example.mydatabaseapp.database


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface LoginDatabaseDao {

    @Insert
    suspend fun insert(login: LoginEntity)

    @Query("SELECT * FROM Login_users_table ORDER BY userId DESC")
    fun getAllUsers(): LiveData<List<LoginEntity>>

    @Query("DELETE FROM Login_users_table")
    suspend fun deleteAll(): Int


}