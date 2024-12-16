package com.danman.sample.topics

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(
                topic.title,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            topic.description?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
            topic.createdAt?.getFormattedValue()?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.labelMedium,
                )
            }
        }
    }
}