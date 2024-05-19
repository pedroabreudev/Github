package com.pedroabreudev.githubitau.ui.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.pedroabreudev.githubitau.R
import com.pedroabreudev.githubitau.viewmodel.RepositoryViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RepositoryListScreen(
    user: String = "android",
    navController: NavController,
    viewModel: RepositoryViewModel = koinViewModel()
) {
    val repositories by viewModel.repositories.observeAsState(emptyList())
    val error by viewModel.error.observeAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchRepositories(user)
    }


    Column {
        TopAppBar(
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        painterResource(
                            id = R.drawable.ic_arrow_back
                        ),
                        contentDescription = "Voltar para a tela anterior"
                    )
                }
            },
            title = { Text(text = "Repositórios Github") })

        if (error != null) {
            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = error ?: "Ocorreu um erro inesperado. tente novamente!",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodyLarge,
            )
        }

        LazyColumn {
            items(repositories) { repository ->
                RepositoryItem(repository = repository) {
                    navController.navigate(route = "repositoryDetails/${repository.id}")
                }
            }
        }
    }

}