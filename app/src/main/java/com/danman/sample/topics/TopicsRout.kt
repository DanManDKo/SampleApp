package com.danman.sample.topics

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun TopicsRout(modifier: Modifier) {
    val vm: TopicsViewModel = hiltViewModel()
    TopicsScreen(modifier)
}