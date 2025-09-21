package com.echonote.presentation.viewmodel


import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.echonote.data.AudioPlayer
import com.echonote.data.AudioRecorder
import com.echonote.presentation.state.VoiceState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.io.File
import javax.inject.Inject


@HiltViewModel
class VoiceRecordingViewModel @Inject constructor(
    private val audioRecorder: AudioRecorder,
    private val audioPlayer: AudioPlayer,
    @ApplicationContext private val  ctx: Context
) :
    ViewModel() {
    val _voiceState = MutableStateFlow(VoiceState())
    val voiceState: StateFlow<VoiceState> = _voiceState.asStateFlow()

    fun updateFile(filename: String) {
        _voiceState.update { current ->
            current.copy(fileName = filename, file = File(filename))
        }
    }

    fun startRecordingAudio() {
        val dir = File(ctx.filesDir, "EchoVoice")
        if(!dir.exists()) dir.mkdirs()
        val file =File(dir,  "${System.currentTimeMillis()}.3gp")
        try {
            audioRecorder.start(file.absolutePath)
            updateFile(file.absolutePath)
        } catch (e: Exception) {
            Log.e("Audio", "There was an error trying to record", e)
        }
    }

    fun stopRecordingAudio() {
        try {
            audioRecorder.stop()
        } catch (e: Exception) {
            Log.e("Audio", "There was an error trying to stop recording", e)
        }
    }

    fun playRecording() {
      if(_voiceState.value.fileName.isEmpty())return
        try {
            audioPlayer.playFile(File(_voiceState.value.fileName))
        } catch (e: Exception) {
            Log.e("Recorded", "There was an error trying to play file", e)
        }
    }
}