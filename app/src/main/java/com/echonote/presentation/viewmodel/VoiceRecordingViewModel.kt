package com.echonote.presentation.viewmodel


import android.content.Context
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.util.Log
import androidx.lifecycle.ViewModel
import com.echonote.MainActivity
import com.echonote.data.AudioRecorder
import com.echonote.data.EchoRecorder

import com.echonote.presentation.state.VoiceState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.io.IOException
import javax.inject.Inject


class VoiceRecordingViewModel @Inject constructor(private val audioRecorder: AudioRecorder): ViewModel() {
    val _voiceState = MutableStateFlow(VoiceState())
    val voiceState: StateFlow<VoiceState> = _voiceState.asStateFlow()
   var context: Context? =null

    fun setContext(ctx: Context){
        context = ctx
    }
    fun startRecordingAudio(){

    }



}