package com.danman.sample.topics

import com.danman.domain.usecases.GetTopicsUseCase
import com.danman.sample.base.BaseViewModel
import com.danman.sample.base.ContentState
import com.danman.sample.di.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@HiltViewModel
class TopicsViewModel @Inject constructor(
    private val getTopicsUseCase: GetTopicsUseCase,
    @IoDispatcher
    private val dispatcher: CoroutineDispatcher
) : BaseViewModel() {

    init {
        fetchTopics()
    }

    fun onReloadClicked() {
        fetchTopics()
    }

    //    TODO: add a string query
    private fun fetchTopics() {
        launch(dispatcher) {
            _contentState.emit(ContentState.Loading)
            val topics = getTopicsUseCase()
            _contentState.emit(ContentState.Content(topics))
        }
    }

}