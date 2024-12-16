package com.danman.domain.model.value

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@JvmInline
value class CreatedAt(val timestamp: Long) {
    fun getFormattedValue(): String {
        val instant = Instant.ofEpochMilli(timestamp)
        val formatter = DateTimeFormatter
            .ofPattern("dd MMM yyyy HH:mm:ss")
            .withZone(ZoneId.systemDefault())
        return formatter.format(instant)
    }
}
