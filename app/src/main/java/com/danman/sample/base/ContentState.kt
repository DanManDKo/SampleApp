package com.danman.sample.base

sealed class ContentState {
    data class Content<T>(val data: T) : ContentState()
    object Loading : ContentState()
    data class Error(val error: Throwable) : ContentState()
//    TODO: add empty
}