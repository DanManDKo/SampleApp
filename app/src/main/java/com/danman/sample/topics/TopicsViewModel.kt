package com.danman.sample.topics

import androidx.lifecycle.viewModelScope
import com.danman.domain.usecases.GetTopicsUseCase
import com.danman.sample.base.BaseViewModel
import com.danman.sample.base.ContentState
import com.danman.sample.di.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class TopicsViewModel @Inject constructor(
    private val getTopicsUseCase: GetTopicsUseCase,
    @IoDispatcher
    private val dispatcher: CoroutineDispatcher
) : BaseViewModel() {

    private var _queryState = MutableStateFlow("")
    val queryState = _queryState.asStateFlow()

    init {
        viewModelScope.launch {
            _queryState.debounce(DEBOUNCE_TIMEOUT).collectLatest {
                fetchTopics(it)
            }
        }
    }

    fun onReloadClicked() {
        fetchTopics(_queryState.value)
    }

    fun submitQuery(query: String) {
        viewModelScope.launch {
            _queryState.emit(query)
        }
    }

    fun clearQuery() {
        submitQuery("")
    }

    private fun fetchTopics(query: String = "") {
        launch(dispatcher) {
            _contentState.emit(ContentState.Loading)
            val topics = getTopicsUseCase(query)
            if (topics.isEmpty()) {
                _contentState.emit(ContentState.Empty)
            } else {
                _contentState.emit(ContentState.Content(topics))
            }
        }
    }

    companion object {
        private const val DEBOUNCE_TIMEOUT = 500L
    }
}