package com.pedroabreudev.githubitau.repository

import com.pedroabreudev.githubitau.model.RepositoryModel
import com.pedroabreudev.githubitau.network.GithubApi
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GithubRepositoryTest {

    private lateinit var api: GithubApi
    private lateinit var repository: GithubRepository

    @Before
    fun setup() {
        api = mockk()
        repository = GithubRepository(api)
    }

    @Test
    fun `getRepository should fetch repositories from Github API`() = runBlocking {
        val repositories = listOf(
            RepositoryModel(1, "Google", "Description1", "Kotlin", 2, 5, openIssuesCount = 10),
            RepositoryModel(2, "Android", "Description2", "Java", 4, 8, openIssuesCount = 20)
        )

        coEvery { api.getRepository(any()) } returns repositories

        val result = repository.getRepository("user")
        assertEquals(repositories, result)
    }

    @Test
    fun `getCachedRepositoryById should return correct repository`() = runBlocking {
        val repositories = listOf(
            RepositoryModel(1, "Google", "Description1", "Kotlin", 2, 5, openIssuesCount = 10),
            RepositoryModel(2, "Android", "Description2", "Java", 4, 8, openIssuesCount = 20)
        )

        coEvery { api.getRepository(any()) } returns repositories

        repository.getRepository("user")

        val repository = repository.getCachedRepositoryById(1)
        assertEquals(repositories[0], repository)
    }
}
