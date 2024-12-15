package com.danman.data.repo

import com.danman.data.datastore.remote.TopicRemoteDataSource
import com.danman.domain.model.Topic
import com.danman.domain.repo.TopicsRepo
import javax.inject.Inject

class TopicsRepoImpl @Inject constructor(private val topicsRepoDataSource: TopicRemoteDataSource) :
    TopicsRepo {
    override suspend fun getTopics(): List<Topic> {
        return topicsRepoDataSource.getTopics()
    }
}