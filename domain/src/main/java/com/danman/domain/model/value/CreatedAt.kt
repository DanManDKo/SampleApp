package com.danman.domain.model.value

@JvmInline
value class CreatedAt(val timestamp: Long) {
    fun getFormattedValue() = timestamp.toString()
}
