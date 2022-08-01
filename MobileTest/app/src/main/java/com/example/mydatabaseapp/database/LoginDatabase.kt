package com.example.mydatabaseapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [LoginEntity::class], version = 1, exportSchema = false)
abstract class LoginDatabase : RoomDatabase() {

    abstract val loginDatabaseDao: LoginDatabaseDao

    companion object {

        @Volatile
        private var INSTANCE: LoginDatabase? = null


        fun getInstance(context: Context): LoginDatabase {
            synchronized(this) {

                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        LoginDatabase::class.java,
                        "user_details_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
