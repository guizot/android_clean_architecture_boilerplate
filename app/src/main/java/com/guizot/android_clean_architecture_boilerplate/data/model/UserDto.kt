package com.guizot.android_clean_architecture_boilerplate.data.model

data class UserDto(
    val login: String?,
    val id: Int?,
    val nodeId: String?,
    val avatarUrl: String?,
    val gravatarId: String?,
    val url: String?,
    val htmlUrl: String?,
    val followersUrl: String?,
    val followingUrl: String?,
    val gistsUrl: String?,
    val starredUrl: String?,
    val subscriptionsUrl: String?,
    val organizationsUrl: String?,
    val reposUrl: String?,
    val eventsUrl: String?,
    val receivedEventsUrl: String?,
    val type: String?,
    val siteAdmin: Boolean?,
    val score: Double?
)
