package com.echonote.data.notes

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns
import io.github.jan.supabase.postgrest.query.Order
import javax.inject.Inject

class NotesRemoteDataSource @Inject constructor(private val supabase: SupabaseClient): NotesDataSource {

    override suspend fun addNote(note: Note): Note?{
       val addNote:Note? =  supabase.postgrest.from("Notes").insert(note).decodeSingle<Note>()
        return addNote
    }

    override  suspend fun getNotes(startRange:Long, endRange:Long): List<NotesDto>?{
        return try {
            supabase.postgrest.from("Notes")
                .select(columns = Columns.list("id","created_at","image_urls","title", "is_ai"), request = {
                    range(startRange..endRange)
                    order("created_at", order = Order.DESCENDING)
                })
                .decodeList<NotesDto>()
        }catch (e: Exception){
            throw e
        }
    }
}