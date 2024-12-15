package com.danman.sample.topics

import com.danman.domain.repo.TopicsRepo
import com.danman.domain.usecases.GetTopicsUseCase
import com.danman.sample.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TopicsViewModel @Inject constructor(private val repo: TopicsRepo) :
    BaseViewModel() {
        init {
            "Ananas"
        }
}