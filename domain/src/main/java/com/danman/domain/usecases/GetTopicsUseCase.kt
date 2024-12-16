package com.danman.domain.usecases

import com.danman.domain.model.Topic
import com.danman.domain.repo.TopicsRepo
import javax.inject.Inject

class GetTopicsUseCase @Inject constructor(private val topicsRepo: TopicsRepo) {

    suspend operator fun invoke(query: String): List<Topic> {
        return topicsRepo.getTopics(query)
    }
}