package com.danman.data.repo

import com.danman.data.datastore.remote.TopicRemoteDataSource
import com.danman.domain.model.Topic
import com.danman.domain.repo.TopicsRepo
import javax.inject.Inject

class TopicsRepoImpl @Inject constructor(private val topicsRepoDataSource: TopicRemoteDataSource) :
    TopicsRepo {
//        TODO: add a database
    override suspend fun getTopics(query: String): List<Topic> {
        return topicsRepoDataSource.getTopics(query)
    }
}