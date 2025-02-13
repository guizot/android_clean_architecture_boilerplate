package com.guizot.android_clean_architecture_boilerplate.domain.model

data class UserEntity(
    val id: Int,
    val login: String,
    val avatarUrl: String,
    val url: String,
)