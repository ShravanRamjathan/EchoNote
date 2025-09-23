package com.echonote.data.notes

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class Note(
    val id: Int = 0,
    val created_at: LocalDateTime?,
    val title: String = "",
    val content: String = "",
    val image_urls: List<String> = emptyList<String>(),
    val is_ai: Boolean = false
    )
