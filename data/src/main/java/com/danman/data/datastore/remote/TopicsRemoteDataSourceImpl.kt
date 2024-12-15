package com.danman.data.datastore.remote

import com.danman.data.mappers.TopicsMapper
import com.danman.domain.model.Topic
import com.example.test.data.TopicDetailsJson
import com.example.test.data.TopicJson
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.supervisorScope
import javax.inject.Inject
import kotlin.random.Random

class TopicsRemoteDataSourceImpl @Inject constructor(private val topicsMapper: TopicsMapper) :
    TopicRemoteDataSource {

    private val statusCodes = listOf(200, 201, 202, 304, 400)

    override suspend fun getTopics(): List<Topic> {
        val topics = getTopicsSimple()
        return supervisorScope {
            val deffs = topics.map { topic ->
                async {
                    topic.id?.let { id ->
                        val details = getTopicDetails(id)
                        topicsMapper(topic, details)
                    }
                }
            }

            deffs.awaitAll().filterNotNull()
        }
    }

    private suspend fun getTopicsSimple(): List<TopicJson> {
        val statusCode = statusCodes.random()
        if (statusCode >= 400) {
            throw IllegalStateException("Did not manage to get topics")
        }
        delay(1000)
        return getTopicsListStubs()
    }

    private suspend fun getTopicDetails(id: String): TopicDetailsJson {
        delay(Random.nextLong(1000, 3 * 1000))
        return getDetailedStub(id)
    }


    private fun getTopicsListStubs(): List<TopicJson> {
        val topics = mutableListOf<TopicJson>()
        repeat(10) { i ->
            topics.add(
                TopicJson(
                    id = "id $i",
                    title = "Tittle $i"
                )
            )
        }
        return topics
    }

    private fun getDetailedStub(id: String): TopicDetailsJson {
        return TopicDetailsJson(
            id = id,
            publishedAt = 111111L,
            description = "Ololo"
        )
    }
}