package com.echonote.presentation.screen

import android.content.Context
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.echonote.presentation.viewmodel.VoiceRecordingViewModel

@Composable
fun VoiceRecordingScreen(voiceRecordingViewModel: VoiceRecordingViewModel, ctx: Context, startRecording:()->Unit, stopRecording:()->Unit) {


    val uiState by voiceRecordingViewModel.voiceState.collectAsStateWithLifecycle()

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {

        VoiceInput(onRecord = {startRecording()}, stopRecording = {stopRecording()})
    }
}

@Composable
fun VoiceInput(onRecord: () -> Unit,stopRecording:()->Unit ) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed = interactionSource.collectIsPressedAsState()
    var counter: Int = 0
    Box(modifier = Modifier.fillMaxWidth().padding(30.dp), contentAlignment = Alignment.BottomCenter, ) {
        Button(modifier = Modifier.width(100.dp).height(40.dp),onClick = {
            if (isPressed.value) {
                onRecord()
                counter=1
            }
            else if(counter>0 && isPressed.value ==false){
                counter = 0
                stopRecording()
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