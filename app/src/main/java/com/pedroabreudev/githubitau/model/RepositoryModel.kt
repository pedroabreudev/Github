package com.pedroabreudev.githubitau.model

import androidx.compose.ui.tooling.preview.datasource.LoremIpsum

data class RepositoryModel(
    val id: Int,
    val name: String,
    val description: String?,
    val language: String?,
) {

    companion object {
        fun mockRepositoryModel() = RepositoryModel(
        id = 0,
        name = "Android",
        description = LoremIpsum(20).values.first(),
        language = LoremIpsum(20).values.first()
        )
    }
}
