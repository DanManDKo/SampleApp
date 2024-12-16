package com.danman.sample.base

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {

    protected val _contentState = MutableStateFlow<ContentState>(ContentState.Loading)
    val contentState = _contentState.asStateFlow()

    protected val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        Log.e("BaseViewModel", "Caught an exception: $exception")
        viewModelScope.launch {
            _contentState.emit(ContentState.Error(exception))
        }
    }

    protected fun launch(
        dispatcher: CoroutineDispatcher,
        block: suspend () -> Unit
    ) {
        viewModelScope.launch(dispatcher + exceptionHandler) {
            block()
        }
    }
}