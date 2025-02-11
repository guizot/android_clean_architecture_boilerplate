package com.guizot.android_clean_architecture_boilerplate.domain.mappers

import com.guizot.android_clean_architecture_boilerplate.domain.model.User
import com.guizot.android_clean_architecture_boilerplate.presentation.github.model.UserUi

fun List<User>.toUi() : List<UserUi> = map {
    UserUi(
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

fun User.toUi() : UserUi = UserUi (
        login = login,
        id = id,
        nodeId = nodeId,
        avatarUrl = avatarUrl,
        gravatarId = gravatarId,
        url = url,
        htmlUrl = htmlUrl,
        followersUrl = followersUrl,
        followingUrl = followingUrl,
        gistsUrl = gistsUrl,
        starredUrl = starredUrl,
        subscriptionsUrl = subscriptionsUrl,
        organizationsUrl = organizationsUrl,
        reposUrl = reposUrl,
        eventsUrl = eventsUrl,
        receivedEventsUrl = receivedEventsUrl,
        type = type,
        siteAdmin = siteAdmin,
        score = score
    )
