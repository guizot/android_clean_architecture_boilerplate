package com.guizot.android_clean_architecture_boilerplate.data.data_source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.guizot.android_clean_architecture_boilerplate.data.model.UserEntityDto
import kotlinx.coroutines.flow.Flow

@Dao
interface GithubUserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(recipe: UserEntityDto)

    @Delete
    suspend fun deleteUser(recipe: UserEntityDto)

    @Query("SELECT * FROM UserEntityDto ORDER BY createdAt DESC")
    fun getAllUsers(): Flow<List<UserEntityDto>>

    @Query("SELECT * FROM UserEntityDto WHERE login = :login LIMIT 1")
    fun getUser(login: String): Flow<UserEntityDto?>

    @Update
    suspend fun updateUser(recipe: UserEntityDto)


}