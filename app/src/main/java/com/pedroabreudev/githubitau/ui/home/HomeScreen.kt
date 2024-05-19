package com.pedroabreudev.githubitau.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.pedroabreudev.githubitau.R
import com.pedroabreudev.githubitau.ui.theme.Size
import com.pedroabreudev.githubitau.viewmodel.HomeViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = koinViewModel()
) {
    val user by viewModel.user.observeAsState("")
    var isError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier
                .width(Size.default.size_120)
                .height(Size.default.size_120),
            painter = painterResource(id = R.drawable.github_logo),
            contentDescription = "Logo do Github"
        )
        OutlinedTextField(
            modifier = Modifier
                .padding(horizontal = Size.default.size_16)
                .fillMaxWidth(),
            value = user,
            onValueChange = {
                viewModel.setUserRepository(it)
                isError = false
            },
            label = {
                Text(text = "Usuário do Github")
            },
            isError = isError && user.isEmpty(),
            singleLine = true
        )
        if (isError && user.isEmpty()) {
            Text(
                modifier = Modifier.padding(horizontal = Size.default.size_16),
                text = "Por favor, insira um nome de usuário.",
                color = Color.Red,

                )
        }
        Button(
            modifier = Modifier
                .padding(horizontal = Size.default.size_16, vertical = Size.default.size_8)
                .fillMaxWidth(),
            onClick = {
                if (user.isNotEmpty()) {
                    navController.navigate("repositoryList/$user")
                } else {
                    isError = true
                }
            },
        ) {
            Text(text = "Entrar")
        }
    }
}



