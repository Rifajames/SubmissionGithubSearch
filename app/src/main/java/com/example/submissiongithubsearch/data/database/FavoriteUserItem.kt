package com.example.submissiongithubsearch.data.database

import com.google.gson.annotations.SerializedName

data class FavoriteUserItem(
    val id: Int?,
    val username: String,
    @SerializedName("avatar_url")
    val avatarUrl: String
)
