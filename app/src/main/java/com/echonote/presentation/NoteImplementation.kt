package com.echonote.presentation

sealed class NoteType(val hasAI: Boolean = false) {
    class VoiceNote() : NoteType(true)
    class Normal : NoteType()
    class AI() : NoteType(true)
}
