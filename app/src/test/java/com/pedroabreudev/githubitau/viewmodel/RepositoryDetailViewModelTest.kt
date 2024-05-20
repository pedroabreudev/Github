package com.pedroabreudev.githubitau.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.pedroabreudev.githubitau.model.RepositoryModel
import com.pedroabreudev.githubitau.repository.GithubRepository
import io.mockk.every
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

class RepositoryDetailViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var repositoryDetailViewModel: RepositoryDetailViewModel
    private lateinit var repository: GithubRepository
    private lateinit var repositoryObserver: Observer<RepositoryModel>

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        repository = mockk()
        repositoryDetailViewModel = RepositoryDetailViewModel(repository)
        repositoryObserver = mockk(relaxed = true)
        repositoryDetailViewModel.repositories.observeForever(repositoryObserver)

        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @Test
    fun `getRepositoryById should update repositories LiveData`() = runBlocking {
        val repositoryData =
            RepositoryModel(1, "Google", "Description1", "Kotlin", 2, 5, openIssuesCount = 10)

        every { repository.getCachedRepositoryById(any()) } returns repositoryData

        repositoryDetailViewModel.getRepositoryById(1)

        verify { repositoryObserver.onChanged(repositoryData) }
    }
}