package com.kriticalflare.composetest.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PlaceholderViewModel : ViewModel(){

    private val _usersLiveData = MutableLiveData<List<UserItem>>()
    val usersLiveData: LiveData<List<UserItem>> = _usersLiveData

    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                delay(2000L)
                _usersLiveData.postValue(PlaceholderRetrofit.service.fetchUsers())
            }
        }
    }
}