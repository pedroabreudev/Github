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
import com.pedroabreudev.githubitau.ui.list.RepositoryListScreen
import com.pedroabreudev.githubitau.ui.theme.GithubItauTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GithubItauTheme {
                // A surface container using the 'background' color from the theme
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
    NavHost(navController = navController, startDestination = "repositoryList") {
        composable(route = "repositoryList") {
            RepositoryListScreen(navController = navController)
        }
        composable(
            route = "repositoryDetails/{repositoryId}",
            arguments = listOf(navArgument(name = "repositoryId") {
                type = NavType.IntType
            })
        ) { backStackEntry ->
            val repositoryId = backStackEntry.arguments?.getInt("repositoryId") ?: return@composable
            RepositoryDetailScreen(repositoryId = repositoryId, navController = navController)
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