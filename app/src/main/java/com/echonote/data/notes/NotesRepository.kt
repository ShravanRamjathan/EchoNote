package com.echonote.data.notes

import javax.inject.Inject

interface NotesRepository  {

   suspend fun createNote(note:Note):Note?
   suspend fun fetchNotes(startRange:Long, endRange:Long):List<NotesDto>?
}