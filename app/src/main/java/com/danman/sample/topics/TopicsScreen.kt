package com.danman.sample.topics

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.danman.domain.model.Topic

@Composable
fun TopicsScreen(modifier: Modifier, topics: List<Topic>) {
    TopicsList(modifier = modifier, topics)
}


@Composable
fun TopicsList(modifier: Modifier, topics: List<Topic>) {
    LazyColumn(modifier = modifier) {
        items(topics.size) {
            TopicItem(topics[it])
        }
    }
}

@Composable
fun TopicItem(topic: Topic) {
    Column {
        Text(topic.title)
        Text(topic.description)
        Text(topic.createdAt.getFormattedValue())
    }
}