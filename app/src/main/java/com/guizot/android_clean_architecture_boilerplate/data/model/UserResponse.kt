package com.guizot.android_clean_architecture_boilerplate.data.model

data class UserResponse<T>(
    val totalCount: Int?,
    val incompleteResults: Boolean?,
    val items: List<T>?
)