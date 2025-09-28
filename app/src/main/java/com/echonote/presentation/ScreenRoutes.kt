package com.echonote.presentation

import kotlinx.serialization.Serializable

sealed class ScreenRoutes {
    @Serializable
    object HomeScreenRoute

    @Serializable
    object VoiceRecordScreenRoute

    @Serializable
    object MainNotesScreenRoute

    @Serializable
    object AddNoteScreenRoute
}