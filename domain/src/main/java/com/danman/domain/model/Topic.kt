package com.danman.domain.model

import com.danman.domain.model.value.CreatedAt

data class Topic(
    val id: String,
    val title: String,
    val description: String,
    val createdAt: CreatedAt
)
