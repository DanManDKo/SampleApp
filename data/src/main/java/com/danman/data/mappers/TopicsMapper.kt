package com.danman.data.mappers

import com.danman.domain.model.Topic
import com.danman.domain.model.value.CreatedAt
import com.example.test.data.TopicDetailsJson
import com.example.test.data.TopicJson
import javax.inject.Inject

class TopicsMapper @Inject constructor() {
    operator fun invoke(topicJson: TopicJson, topicDetailsJson: TopicDetailsJson): Topic {
        return Topic(
            id = topicJson.id ?: "",
            title = topicJson.title ?: "",
            description = topicDetailsJson.description ?: "",
            createdAt = CreatedAt(topicDetailsJson.publishedAt ?: -1L)
        )
    }
}