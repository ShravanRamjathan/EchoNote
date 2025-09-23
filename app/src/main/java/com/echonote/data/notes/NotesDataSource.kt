package com.echonote.data.notes

interface NotesDataSource {

    suspend fun addNote( note:Note):Note?
    suspend fun getNotes(startRange:Long, endRange:Long): List<NotesDto>?
}