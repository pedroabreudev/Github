package com.pedroabreudev.githubitau.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.pedroabreudev.githubitau.model.RepositoryModel
import com.pedroabreudev.githubitau.repository.GithubRepository
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.MockitoAnnotations

class RepositoryViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var repositoryViewModel: RepositoryViewModel
    private lateinit var repository: GithubRepository
    private lateinit var repositoryObserver: Observer<List<RepositoryModel>>

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        repository = mockk()
        repositoryViewModel = RepositoryViewModel(repository)
        repositoryObserver = mockk(relaxed = true)
        repositoryViewModel.repositories.observeForever(repositoryObserver)

        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @Test
    fun `fetchRepositories should fetch repositories correctly`() {
        runBlocking {
            val repositoriesData = listOf(
                RepositoryModel(1, "Google", "Description1", "Kotlin", 2, 5, openIssuesCount = 10),
                RepositoryModel(2, "Android", "Description2", "Java", 4, 8, openIssuesCount = 20)

            )
            coEvery { repository.getRepository("android") } returns repositoriesData

            repositoryViewModel.fetchRepositories("android")

            coEvery { repository.getRepository("android") }

            verify { repositoryObserver.onChanged(repositoriesData) }
        }
    }

    @Test
    fun `should error handling works correctly`() {
        runBlocking {
            val error = Exception("Ocorreu algum erro")

            coEvery { repository.getRepository("") } throws error

            repositoryViewModel.fetchRepositories("")

            verify(exactly = 0) { repositoryObserver.onChanged(any()) }
        }
    }
}
