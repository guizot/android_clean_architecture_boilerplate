package com.guizot.android_clean_architecture_boilerplate.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class UserEntityDto(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val login: String,
    val avatarUrl: String,
    val url: String,
)