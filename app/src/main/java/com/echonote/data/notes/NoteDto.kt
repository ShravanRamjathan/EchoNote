package com.echonote.data.notes

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class NotesDto(
    val id: Int = 0,
    val title: String = "",
    val images: List<String> = emptyList<String>(),
    @Contextual
    val created_at: LocalDateTime? = null
)
