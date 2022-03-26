package com.xlwe.randomuser.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xlwe.randomuser.domain.result.NetworkResult
import com.xlwe.randomuser.domain.usecases.GetUserUseCase
import com.xlwe.randomuser.domain.usecases.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val getUsersUseCase: GetUsersUseCase
) : ViewModel() {

    private val _user: MutableLiveData<NetworkResult> =
        MutableLiveData(NetworkResult.Loading())

    val user: LiveData<NetworkResult>
        get() = _user

    private val _userDB: MutableLiveData<NetworkResult> =
        MutableLiveData(NetworkResult.Loading())

    val userDB: LiveData<NetworkResult>
        get() = _userDB

    init {
        viewModelScope.launch {
            getUserUseCase.getUser().collect {
                _user.postValue(it)
            }

            getUsersUseCase.getUsers().collect {
                Log.d("attadag", "${it.result!!.results}")
                _userDB.postValue(it)
            }
        }
    }

    fun update() = viewModelScope.launch {
        getUserUseCase.getUser().collect {
            _user.postValue(it)
        }
    }

}