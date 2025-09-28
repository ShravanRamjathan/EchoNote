package com.echonote.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.echonote.data.notes.Note
import com.echonote.domain.AddNote
import com.echonote.presentation.state.NoteState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val addNote: AddNote) : ViewModel() {
    private val _noteState = MutableStateFlow(NoteState())
    val noteState: StateFlow<NoteState> = _noteState.asStateFlow()
    fun loading() {

        _noteState.update { currentState ->
            currentState.copy(isLoading = true)
        }
    }

    fun updateSuccess() {

        _noteState.update { currentState ->
            currentState.copy(isSuccess = true, isLoading = false)
        }
    }

    fun notLoading() {

        _noteState.update { currentState ->
            currentState.copy(isLoading = false)
        }
    }


    fun updateContentError(message: String) {
        _noteState.update { currentState ->
            currentState.copy(contentError = message)
        }
    }

    fun validateFields(): Boolean {
        var isValid = true
        val fieldState = _noteState.value
        if (fieldState.content.text.isEmpty()) {
            isValid = false
        }
        if (fieldState.title.text.trim().isEmpty()) {
            isValid = false
        }
        return isValid
    }

    fun onNoteCreate() {
        viewModelScope.launch {
            loading()
            val note = Note(
                content = _noteState.value.content.text.toString(),
                title = _noteState.value.title.text.toString(),

                )
            val resultNote = addNote(note)
            if (resultNote != null) {
                notLoading()
            } else {
                updateSuccess()
            }


        }
    }
}