package com.danman.sample.base

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {

    private val _errorFlow = MutableSharedFlow<Throwable>()
    val errorFlow = _errorFlow.asSharedFlow()

    protected val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        Log.e("BaseViewModel", "Caught an exception: $exception")
        viewModelScope.launch {
            _errorFlow.emit(exception)
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