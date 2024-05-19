package com.pedroabreudev.githubitau.ui.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.pedroabreudev.githubitau.R
import com.pedroabreudev.githubitau.model.RepositoryModel
import com.pedroabreudev.githubitau.ui.theme.FontSize
import com.pedroabreudev.githubitau.ui.theme.Size
import com.pedroabreudev.githubitau.utils.Constants.ONE
import com.pedroabreudev.githubitau.viewmodel.RepositoryDetailViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun RepositoryDetailScreen(
    repositoryId: Int,
    navController: NavController,
    viewModel: RepositoryDetailViewModel = koinViewModel()
) {

    val repository = viewModel.repositories.observeAsState()

    LaunchedEffect(repositoryId) {
        viewModel.getRepositoryById(repositoryId)
    }

    repository.value?.let {
        RepositoryDetail(repository = it, navController = navController)
    } ?: run {
        Text(
            modifier = Modifier.padding(Size.default.size_16),
            text = "Repositório não existe"
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RepositoryDetail(repository: RepositoryModel, navController: NavController) {

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
            title = {
                Text(text = "Detalhes do Repositório")
            })
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Size.default.size_10)
        ) {
            Column(
                modifier = Modifier
                    .padding(Size.default.size_16)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = "Nome: ${repository.name}",
                    maxLines = ONE,
                    fontSize = FontSize.default.fontSize_md
                )

                repository.description?.let {
                    Spacer(modifier = Modifier.height(Size.default.size_6))
                    Text(
                        text = "Descrição: ${repository.description}",
                        fontSize = FontSize.default.fontSize_sm
                    )

                }
                Spacer(modifier = Modifier.height(Size.default.size_16))

                Column {
                    Row {
                        IconDetail(
                            painter = R.drawable.ic_star_border,
                            description = "ícone estrela"
                        )
                        Spacer(modifier = Modifier.width(Size.default.size_4))
                        Text(text = "Stars: ${repository.stargazersCount}")

                        Spacer(modifier = Modifier.width(Size.default.size_16))

                        IconDetail(painter = R.drawable.ic_fork, description = "ícone fork")
                        Spacer(modifier = Modifier.width(Size.default.size_4))
                        Text(text = "Forks: ${repository.forksCount}")

                        Spacer(modifier = Modifier.width(Size.default.size_16))

                        IconDetail(painter = R.drawable.ic_filter, description = "ícone problemas")
                        Spacer(modifier = Modifier.width(Size.default.size_4))
                        Text(text = "Issues: ${repository.openIssuesCount}")
                    }
                }
            }
        }
    }
}

@Composable
private fun IconDetail(painter: Int, description: String = "") {
    Icon(
        modifier = Modifier
            .height(Size.default.size_24)
            .width(Size.default.size_24),
        painter = painterResource(id = painter), contentDescription = description
    )
}