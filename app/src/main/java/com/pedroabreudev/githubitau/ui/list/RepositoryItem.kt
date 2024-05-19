package com.pedroabreudev.githubitau.ui.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.pedroabreudev.githubitau.model.RepositoryModel
import com.pedroabreudev.githubitau.ui.theme.FontSize
import com.pedroabreudev.githubitau.ui.theme.Size
import com.pedroabreudev.githubitau.utils.Constants

@Composable
fun RepositoryItem(
    repository: RepositoryModel,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(Size.default.size_10)
    )
    {
        Column(modifier = Modifier.padding(Size.default.size_16)) {
            Text(
                text = "Nome: " + repository.name,
                maxLines = Constants.ONE,
                fontSize = FontSize.default.fontSize_md,
                fontWeight = FontWeight.Bold
            )

            repository.description?.let {
                Spacer(modifier = Modifier.height(Size.default.size_6))
                Text(
                    text = "Descrição: " + repository.description,
                    fontSize = FontSize.default.fontSize_sm
                )

            }

            repository.language?.let {
                Spacer(modifier = Modifier.height(Size.default.size_6))
                Text(
                    text = "Linguagem: " + repository.language,
                    maxLines = Constants.ONE,
                    fontSize = FontSize.default.fontSize_xs
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RepositoryItemPreview() {
    Column {
        RepositoryItem(repository = RepositoryModel.mockRepositoryModel()) {}
        RepositoryItem(repository = RepositoryModel.mockRepositoryModel()) {}
        RepositoryItem(repository = RepositoryModel.mockRepositoryModel()) {}
    }
}