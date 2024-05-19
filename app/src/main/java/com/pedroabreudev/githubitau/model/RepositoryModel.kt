package com.pedroabreudev.githubitau.model

import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import com.google.gson.annotations.SerializedName

data class RepositoryModel(
    val id: Int,
    val name: String,
    val description: String?,
    val language: String?,
    @SerializedName("stargazers_count")
    val stargazersCount: Int?,
    @SerializedName("forks_count")
    val forksCount: Int?,
    @SerializedName("open_issues_count")
    val openIssuesCount: Int?
) {

    companion object {
        fun mockRepositoryModel() = RepositoryModel(
            id = 0,
            name = "Android",
            description = LoremIpsum(20).values.first(),
            language = LoremIpsum(20).values.first(),
            stargazersCount = null,
            forksCount = null,
            openIssuesCount = null
        )
    }
}
