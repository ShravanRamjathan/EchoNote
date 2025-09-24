package com.echonote.domain

import android.util.Log
import com.echonote.data.notes.Note
import com.echonote.data.notes.NotesRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AddNote @Inject constructor(private val notesRepository: NotesRepository) {

    suspend operator  fun invoke(note: Note): Note? {

        return try {
            withContext(Dispatchers.IO) {
                notesRepository.createNote(note)
            }
        } catch (e: Exception) {
            Log.e("Notes", "Something went wrong", e)
            return null
        }
    }
}