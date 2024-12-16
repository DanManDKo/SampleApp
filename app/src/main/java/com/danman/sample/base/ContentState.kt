package com.danman.sample.base

sealed class ContentState {
    data class Content<T>(val data: T) : ContentState()
    object Loading : ContentState()
    object Empty : ContentState()
    data class Error(val error: Throwable) : ContentState()
}