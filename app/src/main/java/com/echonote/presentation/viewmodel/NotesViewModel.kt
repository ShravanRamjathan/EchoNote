package com.echonote.presentation.viewmodel

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import com.echonote.presentation.state.NotesOverViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class NotesViewModel (): ViewModel() {
private val _notesOverViewState = MutableStateFlow(NotesOverViewState())
val notesOverViewState: StateFlow<NotesOverViewState> = _notesOverViewState.asStateFlow()


}