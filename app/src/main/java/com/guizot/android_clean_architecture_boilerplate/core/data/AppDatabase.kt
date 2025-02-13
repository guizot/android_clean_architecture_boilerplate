package com.guizot.android_clean_architecture_boilerplate.core.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.guizot.android_clean_architecture_boilerplate.data.data_source.local.GithubUserDao
import com.guizot.android_clean_architecture_boilerplate.data.model.UserEntityDto

@Database(entities = [UserEntityDto::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        fun getInstance(context: Context) =
            Room.databaseBuilder(context, AppDatabase::class.java, "app_db")
                .fallbackToDestructiveMigration()
                .build()
    }

    abstract fun getGithubUserDao(): GithubUserDao

}