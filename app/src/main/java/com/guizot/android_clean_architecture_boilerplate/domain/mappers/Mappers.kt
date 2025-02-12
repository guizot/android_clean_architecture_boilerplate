package com.guizot.android_clean_architecture_boilerplate.domain.mappers

import com.guizot.android_clean_architecture_boilerplate.domain.model.User
import com.guizot.android_clean_architecture_boilerplate.domain.model.UserDetail
import com.guizot.android_clean_architecture_boilerplate.presentation.github.model.UserDetailUi
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


fun UserDetail.toUi(): UserDetailUi {
    return UserDetailUi(
        login = this.login,
        id = this.id,
        nodeId = this.nodeId,
        avatarUrl = this.avatarUrl,
        gravatarId = this.gravatarId,
        url = this.url,
        htmlUrl = this.htmlUrl,
        followersUrl = this.followersUrl,
        followingUrl = this.followingUrl,
        gistsUrl = this.gistsUrl,
        starredUrl = this.starredUrl,
        subscriptionsUrl = this.subscriptionsUrl,
        organizationsUrl = this.organizationsUrl,
        reposUrl = this.reposUrl,
        eventsUrl = this.eventsUrl,
        receivedEventsUrl = this.receivedEventsUrl,
        type = this.type,
        siteAdmin = this.siteAdmin,
        name = this.name,
        company = this.company,
        blog = this.blog,
        location = this.location,
        email = this.email,
        hireable = this.hireable,
        bio = this.bio,
        twitterUsername = this.twitterUsername,
        publicRepos = this.publicRepos,
        publicGists = this.publicGists,
        followers = this.followers,
        following = this.following,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt
    )
}

fun UserDetail.toList(): List<Pair<String, Any?>> {
    return listOf(
        "avatarUrl" to avatarUrl,
        "login" to login,
        "id" to id,
        "nodeId" to nodeId,
        "gravatarId" to gravatarId,
        "url" to url,
        "htmlUrl" to htmlUrl,
        "followersUrl" to followersUrl,
        "followingUrl" to followingUrl,
        "gistsUrl" to gistsUrl,
        "starredUrl" to starredUrl,
        "subscriptionsUrl" to subscriptionsUrl,
        "organizationsUrl" to organizationsUrl,
        "reposUrl" to reposUrl,
        "eventsUrl" to eventsUrl,
        "receivedEventsUrl" to receivedEventsUrl,
        "type" to type,
        "siteAdmin" to siteAdmin,
        "name" to name,
        "company" to company,
        "blog" to blog,
        "location" to location,
        "email" to email,
        "hireable" to hireable,
        "bio" to bio,
        "twitterUsername" to twitterUsername,
        "publicRepos" to publicRepos,
        "publicGists" to publicGists,
        "followers" to followers,
        "following" to following,
        "createdAt" to createdAt,
        "updatedAt" to updatedAt
    )
}