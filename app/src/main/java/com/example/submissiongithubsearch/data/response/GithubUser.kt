package com.example.submissiongithubsearch.data.response

import com.google.gson.annotations.SerializedName

data class ResponseGithub(
    val totalCount: Int,
    val incompleteResults: Boolean,
    val items: List<ItemsItem>
)

data class ItemsItem(
    val gistsUrl: String,
    val reposUrl: String,
    val followingUrl: String,
    val starredUrl: String,
    val login: String,
    val followersUrl: String,
    val type: String,
    val url: String,
    val subscriptionsUrl: String,
    val score: Double,
    val receivedEventsUrl: String,
    @SerializedName("avatar_url")
    val avatarUrl: String,
    val eventsUrl: String,
    val htmlUrl: String,
    val siteAdmin: Boolean,
    val id: Int,
    val gravatarId: String,
    val nodeId: String,
    val organizationsUrl: String
)