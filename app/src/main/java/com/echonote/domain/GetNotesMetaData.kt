package com.echonote.domain

import android.util.Log
import com.echonote.data.notes.NotesDto
import com.echonote.data.notes.NotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetNotesMetaData @Inject constructor(private val notesRepository: NotesRepository) {

    suspend operator fun invoke(startRange: Long, endRange: Long):List<NotesDto>? {
        return try {
            withContext(Dispatchers.IO) {
                notesRepository.fetchNotes(startRange = startRange, endRange)
            }
        }catch (e: Exception){
            Log.d("Notes", "")
            return emptyList()
        }
    }
}