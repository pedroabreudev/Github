package com.pedroabreudev.githubitau.repository

import com.pedroabreudev.githubitau.network.GithubApi

class GithubRepository(private val api: GithubApi) {
    suspend fun getRepository(user: String) = api.getRepository(user)
}