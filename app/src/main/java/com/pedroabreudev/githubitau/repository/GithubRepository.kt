package com.pedroabreudev.githubitau.repository

import com.pedroabreudev.githubitau.model.RepositoryModel
import com.pedroabreudev.githubitau.network.GithubApi

class GithubRepository(private val api: GithubApi) {

    private var cachedRepository: List<RepositoryModel> = emptyList()
    suspend fun getRepository(user: String): List<RepositoryModel> {
        cachedRepository = api.getRepository(user)
        return cachedRepository
    }

    fun getCachedRepositoryById(repositoryId: Int): RepositoryModel? {
        return cachedRepository.find { it.id == repositoryId }
    }
}