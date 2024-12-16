package com.danman.data.datastore.remote

import com.danman.domain.model.Topic

interface TopicRemoteDataSource {
    suspend fun getTopics(query: String): List<Topic>
}