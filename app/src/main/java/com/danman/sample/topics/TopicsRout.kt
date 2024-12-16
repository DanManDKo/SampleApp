@file:Suppress("UNCHECKED_CAST")

package com.danman.sample.topics

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.danman.domain.model.Topic
import com.danman.sample.R
import com.danman.sample.base.ContentState

@Composable
fun TopicsRout(modifier: Modifier) {
    val vm: TopicsViewModel = hiltViewModel()
    val contentState = vm.contentState.collectAsState(ContentState.Loading)
    val value = contentState.value
    when (value) {
        is ContentState.Content<*> -> {
            TopicsScreen(
                modifier = modifier,
                topics = value.data as List<Topic>
            )
        }

        is ContentState.Error -> {
            TopicsError {
                vm.onReloadClicked()
            }
        }

        is ContentState.Loading -> {
            Loading()
        }
    }
}

@Composable
private fun TopicsError(onReloadClick: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = stringResource(id = R.string.title_something_went_wrong),
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedButton(onClick = onReloadClick) {
                Text(text = stringResource(R.string.btn_reload))
                Spacer(modifier = Modifier.width(8.dp))
                Icon(imageVector = Icons.Default.Refresh, contentDescription = "refresh")
            }
        }
    }
}

@Composable
private fun Loading() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}