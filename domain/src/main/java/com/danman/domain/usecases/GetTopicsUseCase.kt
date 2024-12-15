package com.danman.domain.usecases

import com.danman.domain.model.Topic
import com.danman.domain.repo.TopicsRepo
import javax.inject.Inject

class GetTopicsUseCase @Inject constructor(private val topicsRepo: TopicsRepo) {

    operator fun invoke(): List<Topic> {
        return emptyList()
    }
}