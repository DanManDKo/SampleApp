package com.danman.domain.repo

import com.danman.domain.model.Topic

interface TopicsRepo {
    suspend fun getTopics(query: String): List<Topic>
}