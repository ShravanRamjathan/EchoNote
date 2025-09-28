package com.echonote.presentation.state

import com.echonote.data.notes.NotesDto

data class NotesOverViewState(
    val numNotes: Int = 0,
    val currentItem:Long = 0,
    val itemRange:Long = 10,
    val noteMetaData:List<NotesDto> = emptyList<NotesDto>(),

    val isLoading:Boolean = false,
    val isSuccess: Boolean = false,
    val errorMessage:String = ""
    )