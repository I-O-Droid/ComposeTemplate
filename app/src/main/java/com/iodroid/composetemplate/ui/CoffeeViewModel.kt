package com.iodroid.composetemplate.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iodroid.composetemplate.data.local.repo.CoffeeLocalRepository
import com.iodroid.composetemplate.data.models.Coffee
import com.iodroid.composetemplate.data.remote.repo.CoffeeRemoteRepository
import com.iodroid.composetemplate.data.sharedpreference.SharedPref
import com.iodroid.composetemplate.utils.Constants.TAG
import com.iodroid.composetemplate.utils.HttpRoutes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoffeeViewModel @Inject constructor(
    val remoteRepo: CoffeeRemoteRepository,
    val localRepo: CoffeeLocalRepository,
    val sharedPref: SharedPref
): ViewModel() {

    val remoteCoffeeStateFlow = MutableStateFlow(0)
    val localCoffeeStateFlow = MutableStateFlow(emptyList<Coffee>())

    // haven't used them in the ui but created for future use cases
    var isLoading by mutableStateOf(false)
    var errorState by mutableStateOf("")

    fun getRemoteHotCoffee() {
        viewModelScope.launch {
            kotlin.runCatching {
                isLoading = true
                remoteRepo.getCoffee(HttpRoutes.HOT)
            }.onSuccess {
                isLoading = false
                errorState = ""
                Log.d(TAG, "getRemoteHotCoffee: $it")
                remoteCoffeeStateFlow.value = it.size
            }.onFailure {
                isLoading = false
                Log.d(TAG, "getRemoteHotCoffee: $it")
                errorState = "Something went wrong!"
            }
        }
    }

    fun getRemoteIcedCoffee() {
        viewModelScope.launch {
            kotlin.runCatching {
                isLoading = true
                remoteRepo.getCoffee(HttpRoutes.ICED)
            }.onSuccess {
                isLoading = false
                errorState = ""
                remoteCoffeeStateFlow.value = it.size
            }.onFailure {
                isLoading = false
                errorState = "Something went wrong!"
            }
        }
    }

    fun getAllLocalCoffee() {
        viewModelScope.launch {
            localRepo.getAllCoffee().collect {
                localCoffeeStateFlow.value = it
                Log.d(TAG, "getAllLocalCoffee: ${localCoffeeStateFlow.value}")
            }
        }
    }

    fun insertCoffee() {
        viewModelScope.launch {
            localRepo.insertCoffee(
                Coffee(id = 1)
            )
            getAllLocalCoffee()
        }
    }

    fun setValue(){
        viewModelScope.launch{
            sharedPref.setValue("Coffee")
        }
    }
}