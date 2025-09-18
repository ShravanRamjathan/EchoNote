package com.echonote.presentation.screen

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.echonote.presentation.viewmodel.VoiceRecordingViewModel

@Composable
fun VoiceRecordingScreen(voiceRecordingViewModel: VoiceRecordingViewModel) {


    val uiState by voiceRecordingViewModel.voiceState.collectAsStateWithLifecycle()
    Box(modifier = Modifier.fillMaxSize()) {

        VoiceInput(onRecord = {voiceRecordingViewModel.startRecording()})
    }
}

@Composable
fun VoiceInput(onRecord: () -> Unit, ) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed = interactionSource.collectIsPressedAsState()
    Box() {
        Button(onClick = {
            if (isPressed.value) {
                onRecord()
            }
        }, interactionSource = interactionSource) {
            if (isPressed.value) {
                Text("Recording")
            } else {
                Icon(Icons.Default.PlayArrow, "Record or something")
            }

        }
    }
}