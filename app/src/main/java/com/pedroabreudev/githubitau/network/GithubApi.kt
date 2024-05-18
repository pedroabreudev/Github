package com.pedroabreudev.githubitau.network

import com.pedroabreudev.githubitau.model.RepositoryModel
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {
    @GET("users/{user}/repos")
    suspend fun getRepository(@Path("user") user: String): List<RepositoryModel>
}