package com.pedroabreudev.githubitau.utils

sealed class Screen(val route: String) {
    data object Home : Screen("homeScreen")
    data object RepositoryList : Screen("repositoryList/{user}") {
        const val ARG_USER = "user"
    }
    data object RepositoryDetail : Screen("repositoryDetails/{repositoryId}") {
        const val ARG_REPOSITORY_ID = "repositoryId"
    }
}