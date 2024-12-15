package com.danman.sample.topics

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun TopicsRout(modifier: Modifier) {
    val vm: TopicsViewModel = hiltViewModel()
    val topics = vm.topicsFlow.collectAsState(initial = emptyList()).value
    val error = vm.errorFlow.collectAsState(null).value
    if (error == null) {
        TopicsScreen(modifier = modifier, topics = topics)
    } else {
        TopicsErrorScreen(error = error)
    }
}

//TODO: add reload logic
@Composable
private fun TopicsErrorScreen(error: Throwable) {
    val snackbarHostState = remember { SnackbarHostState() }
    LaunchedEffect(error) {
        snackbarHostState.showSnackbar("Error: ${error.message}")
    }

    Box(modifier = Modifier.fillMaxSize()) {
        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}