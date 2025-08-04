package com.developer.rickandmorty.core.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

open class BaseViewModel : ViewModel(){
    private val _loadingState = MutableStateFlow (false)
    val loadingState: StateFlow<Boolean> = _loadingState

    fun showLoading() {
        _loadingState.value = true
    }

    fun hideLoading() {
        _loadingState.value = false
    }

}