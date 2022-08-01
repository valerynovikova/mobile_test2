package com.example.mydatabaseapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Login_users_table")
data class LoginEntity(

    @PrimaryKey(autoGenerate = true)

    @ColumnInfo(name = "email")
    var email: String,

    @ColumnInfo(name = "password_text")
    var password: String
)