package com.xlwe.randomuser.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xlwe.randomuser.domain.result.NetworkResult
import com.xlwe.randomuser.domain.result.Status
import com.xlwe.randomuser.domain.usecases.GetUserUseCase
import com.xlwe.randomuser.domain.usecases.GetUsersUseCase
import com.xlwe.randomuser.presentation.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val getUsersUseCase: GetUsersUseCase
) : ViewModel() {

    private val _userDB: MutableLiveData<NetworkResult> =
        MutableLiveData(NetworkResult.Loading())

    val userDB: LiveData<NetworkResult>
        get() = _userDB

    private val _networkLoading: MutableLiveData<Unit> =
        MutableLiveData(Unit)

    val networkLoading: LiveData<Unit>
        get() = _networkLoading

    private val _picture: MutableLiveData<String> =
        MutableLiveData()

    val picture: LiveData<String>
        get() = _picture

    private val _fullName: MutableLiveData<String> =
        MutableLiveData()

    val fullName: LiveData<String>
        get() = _fullName

    private val _dob: MutableLiveData<String> =
        MutableLiveData()

    val dob: LiveData<String>
        get() = _dob

    private val _phone: MutableLiveData<String> =
        MutableLiveData()

    val phone: LiveData<String>
        get() = _phone

    private val _country: MutableLiveData<String> =
        MutableLiveData()

    val country: LiveData<String>
        get() = _country

    private val _city: MutableLiveData<String> =
        MutableLiveData()

    val city: LiveData<String>
        get() = _city

    private val _state: MutableLiveData<String> =
        MutableLiveData()

    val state: LiveData<String>
        get() = _state

    private val _coordinates: MutableLiveData<String> =
        MutableLiveData()

    val coordinates: LiveData<String>
        get() = _coordinates

    private val _noConnection: MutableLiveData<Unit> =
        MutableLiveData()

    val noConnection: LiveData<Unit>
        get() = _noConnection

    private val _serviceUnavailable: MutableLiveData<Unit> =
        MutableLiveData()

    val serviceUnavailable: LiveData<Unit>
        get() = _serviceUnavailable

    init {
        getData()
    }

    fun update() = viewModelScope.launch {
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            getUserUseCase.getUser().collect {
                when (it) {
                    is NetworkResult.Loading -> {
                        _networkLoading.postValue(Unit)
                    }
                    is NetworkResult.Success -> {
                        it.result?.apply {
                            val res = results[Constants.INDEX]

                            _picture.postValue(res.picture.large)

                            _fullName.postValue(
                                res.name.title + " " + res.name.first + " " + res.name.last
                            )

                            val date = res.dob.date.substring(
                                Constants.START_SUBSTRING,
                                Constants.END_SUBSTRING
                            ).replace("-", ".").split(".").asReversed()

                            var fullDate = ""

                            for (d in date.indices) {
                                if (d != date.size - Constants.GET_INDEX_LAST_ELEMENT)
                                    fullDate += date[d] + "."
                                else
                                    fullDate += date[d]
                            }

                            _dob.postValue(fullDate)

                            _phone.postValue(res.phone)

                            _country.postValue(res.location.country)
                            _city.postValue(res.location.city)
                            _state.postValue(res.location.state)

                            _coordinates.postValue(res.location.coordinates.latitude + " " + res.location.coordinates.longitude)
                        }
                    }
                    is NetworkResult.Error -> {
                        if (it.status == Status.NO_CONNECTION) {
                            _noConnection.postValue(Unit)
                        } else {
                            _serviceUnavailable.postValue(Unit)
                        }
                    }
                }
            }

            getUsersUseCase.getUsers().collect {
                _userDB.postValue(it)
            }
        }
    }

}
