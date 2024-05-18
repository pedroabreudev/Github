package com.pedroabreudev.githubitau.di

import com.pedroabreudev.githubitau.network.GithubApi
import com.pedroabreudev.githubitau.network.NetworkConstants.BASE_URL
import com.pedroabreudev.githubitau.repository.GithubRepository
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single { provideRetrofit() }
    single { provideGithubApi(get()) }
    single { GithubRepository(get()) }

}

fun provideRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(OkHttpClient.Builder().build())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun provideGithubApi(retrofit: Retrofit): GithubApi {
    return retrofit.create(GithubApi::class.java)
}