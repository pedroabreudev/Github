package com.pedroabreudev.githubitau.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class HomeViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var homeViewModel: HomeViewModel

    @Mock
    private lateinit var observer: Observer<String>

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        homeViewModel = HomeViewModel()
    }

    @Test
    fun testSetUserRepository() {
        val testUserRepository = "android"

        homeViewModel.user.observeForever(observer)

        homeViewModel.setUserRepository(testUserRepository)

        assertEquals(testUserRepository, homeViewModel.user.value)
    }
}