package com.pedroabreudev.githubitau.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pedroabreudev.githubitau.model.RepositoryModel
import com.pedroabreudev.githubitau.repository.GithubRepository
import kotlinx.coroutines.launch

class RepositoryViewModel(
    private val repository: GithubRepository
) : ViewModel() {

    private val _repository = MutableLiveData<List<RepositoryModel>>()

    val repositories: LiveData<List<RepositoryModel>> get() = _repository

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error


    fun fetchRepositories(user: String) {
        viewModelScope.launch {
            try {
                val reposlist = repository.getRepository(user)
                _repository.postValue(reposlist)
            } catch (e: Exception) {
                _error.postValue(e.message)
            }
        }
    }
}