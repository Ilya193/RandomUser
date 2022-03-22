package com.xlwe.randomuser.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xlwe.randomuser.domain.result.NetworkResult
import com.xlwe.randomuser.domain.usecases.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
) : ViewModel() {

    private val _user: MutableLiveData<NetworkResult> =
        MutableLiveData(NetworkResult.Loading())

    val user: LiveData<NetworkResult>
        get() = _user

    init {
        viewModelScope.launch {
            getUserUseCase.getUser().collect {
                _user.postValue(it)
            }
        }
    }

}