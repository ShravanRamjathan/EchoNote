package com.echonote.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.echonote.data.notes.NotesDto
import com.echonote.domain.GetNotesMetaData
import com.echonote.presentation.state.NotesOverViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainNotesViewModel @Inject constructor(

    private val getNotesMetadata: GetNotesMetaData,
) : ViewModel() {
    private val _notesOverViewState = MutableStateFlow(NotesOverViewState())
    val notesOverViewState: StateFlow<NotesOverViewState> = _notesOverViewState.asStateFlow()


    fun updatePagination() {
        val updatedItemPos = _notesOverViewState.value.currentItem + 10
        _notesOverViewState.update { currentState ->
            currentState.copy(currentItem = updatedItemPos)
        }
    }

    fun updateErrorMessage(message: String) {
        _notesOverViewState.update {
            it.copy(errorMessage = message, isLoading = false, isSuccess = false)
        }
    }

    fun fetchNotes() {
        viewModelScope.launch {
            val pagination = _notesOverViewState.value
            val notes: List<NotesDto>? =
                getNotesMetadata(pagination.currentItem, pagination.itemRange)
            if (notes == null) {

                updateErrorMessage("Current user does not have any notes")
                return@launch
            } else {
                _notesOverViewState.update {
                    it.copy(noteMetaData = notes, isLoading = false, isSuccess = true)
                }
                updatePagination()
            }

        }
    }


}

