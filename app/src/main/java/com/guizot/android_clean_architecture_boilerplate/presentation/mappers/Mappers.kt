package com.guizot.android_clean_architecture_boilerplate.presentation.mappers

import com.guizot.android_clean_architecture_boilerplate.domain.model.UserEntity
import com.guizot.android_clean_architecture_boilerplate.presentation.github.model.UserDetailUi
import com.guizot.android_clean_architecture_boilerplate.presentation.github.model.UserEntityUi

fun UserEntityUi.toDomain() : UserEntity = UserEntity(
    login = login,
    id = id,
    avatarUrl = avatarUrl,
    url = url,
)

fun UserDetailUi.toEntity() : UserEntityUi = UserEntityUi (
    login = login ?: "",
    id = id ?: 0,
    avatarUrl = avatarUrl ?: "",
    url = url ?: "",
)