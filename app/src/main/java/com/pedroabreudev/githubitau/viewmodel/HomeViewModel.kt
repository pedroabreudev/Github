package com.pedroabreudev.githubitau.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    private val _user = MutableLiveData("")
    val user: LiveData<String> = _user

    fun setUserRepository(newUser: String) {
        _user.value = newUser
    }
}