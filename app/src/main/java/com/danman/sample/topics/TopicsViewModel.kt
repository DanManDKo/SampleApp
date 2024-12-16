package com.danman.sample.topics

import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import com.danman.domain.model.Topic
import com.danman.domain.usecases.GetTopicsUseCase
import com.danman.sample.base.BaseViewModel
import com.danman.sample.di.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

@HiltViewModel
class TopicsViewModel @Inject constructor(
    private val getTopicsUseCase: GetTopicsUseCase,
    @IoDispatcher
    private val dispatcher: CoroutineDispatcher
) : BaseViewModel() {

    private val _topicsFlow = MutableSharedFlow<List<Topic>>()
    val topicsFlow = _topicsFlow.asSharedFlow()

    init {
        fetchTopics()
    }

    //    TODO: add a string query
    private fun fetchTopics() {
        launch(dispatcher) {
            val topics = getTopicsUseCase()
            _topicsFlow.emit(topics)
        }
    }

}