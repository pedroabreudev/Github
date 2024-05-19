package com.pedroabreudev.githubitau.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pedroabreudev.githubitau.model.RepositoryModel
import com.pedroabreudev.githubitau.repository.GithubRepository
import kotlinx.coroutines.launch

class RepositoryDetailViewModel(
    private val repository: GithubRepository
) : ViewModel() {

    private val _repository = MutableLiveData<RepositoryModel>()
    val repositories: LiveData<RepositoryModel> get() = _repository

    fun getRepositoryById(repositoryId: Int){
        viewModelScope.launch {
            val repository = repository.getCachedRepositoryById(repositoryId)
            _repository.postValue(repository!!)
        }
    }
}