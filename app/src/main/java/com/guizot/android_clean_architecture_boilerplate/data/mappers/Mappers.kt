package com.guizot.android_clean_architecture_boilerplate.data.mappers

import com.guizot.android_clean_architecture_boilerplate.data.model.UserDto
import com.guizot.android_clean_architecture_boilerplate.domain.model.User

fun List<UserDto>.toDomain() : List<User> = map {
    User(
        login = it.login,
        id = it.id,
        nodeId = it.nodeId,
        avatarUrl = it.avatarUrl,
        gravatarId = it.gravatarId,
        url = it.url,
        htmlUrl = it.htmlUrl,
        followersUrl = it.followersUrl,
        followingUrl = it.followingUrl,
        gistsUrl = it.gistsUrl,
        starredUrl = it.starredUrl,
        subscriptionsUrl = it.subscriptionsUrl,
        organizationsUrl = it.organizationsUrl,
        reposUrl = it.reposUrl,
        eventsUrl = it.eventsUrl,
        receivedEventsUrl = it.receivedEventsUrl,
        type = it.type,
        siteAdmin = it.siteAdmin,
        score = it.score
    )
}