package com.example.submissiongithubsearch.data.retrofit

import com.example.submissiongithubsearch.data.response.DetailResponse
import com.example.submissiongithubsearch.data.response.ResponseFollow
import com.example.submissiongithubsearch.data.response.ResponseGithub
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApiService {
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getUser(
        @Query("q")
        query: String
    ): Call<ResponseGithub>

    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun getGithubDetail(
        @Path("username")
        username: String
    ): Call<DetailResponse>

    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")
    fun getFollower(
        @Path("username")
        username: String
    ): Call<List<ResponseFollow>>

    @GET("users/{username}/following")
    @Headers("Authorization: token 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")
    fun getFollowing(
        @Path("username")
        username: String
    ): Call<List<ResponseFollow>>
}