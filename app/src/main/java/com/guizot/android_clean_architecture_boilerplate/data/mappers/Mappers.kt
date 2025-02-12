package com.guizot.android_clean_architecture_boilerplate.data.mappers

import com.guizot.android_clean_architecture_boilerplate.data.model.UserDetailDto
import com.guizot.android_clean_architecture_boilerplate.data.model.UserDto
import com.guizot.android_clean_architecture_boilerplate.domain.model.User
import com.guizot.android_clean_architecture_boilerplate.domain.model.UserDetail

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

fun UserDetailDto.toDomain(): UserDetail {
    return UserDetail(
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
