package com.pedroabreudev.githubitau

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.pedroabreudev.githubitau.ui.detail.RepositoryDetailScreen
import com.pedroabreudev.githubitau.ui.home.HomeScreen
import com.pedroabreudev.githubitau.ui.list.RepositoryListScreen
import com.pedroabreudev.githubitau.ui.theme.GithubItauTheme
import com.pedroabreudev.githubitau.utils.Screen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GithubItauTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    App()
                }
            }
        }
    }
}

@Composable
fun App() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(
            Screen.RepositoryList.route,
            arguments = listOf(navArgument(Screen.RepositoryList.ARG_USER) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val user =
                backStackEntry.arguments?.getString(Screen.RepositoryList.ARG_USER) ?: "android"
            RepositoryListScreen(navController = navController, user = user)
        }
        composable(
            Screen.RepositoryDetail.route,
            arguments = listOf(navArgument(Screen.RepositoryDetail.ARG_REPOSITORY_ID) {
                type = NavType.IntType
            })
        ) { backStackEntry ->
            val repositoryId =
                backStackEntry.arguments?.getInt(Screen.RepositoryDetail.ARG_REPOSITORY_ID)
            repositoryId?.let { id ->
                RepositoryDetailScreen(repositoryId = id, navController = navController)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AppPreview() {
    GithubItauTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            App()
        }
    }
}