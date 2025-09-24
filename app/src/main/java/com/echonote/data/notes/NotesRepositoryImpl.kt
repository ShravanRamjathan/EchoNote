package com.echonote.data.notes

import javax.inject.Inject

class NotesRepositoryImpl @Inject constructor(private val notesDataSource: NotesDataSource): NotesRepository {
    override suspend fun createNote(note: Note): Note? {
        return notesDataSource.addNote(note)
    }

    override suspend fun fetchNotes(
        startRange: Long,
        endRange: Long,
    ): List<NotesDto>? {
        return notesDataSource.getNotes(startRange, endRange)
    }
}