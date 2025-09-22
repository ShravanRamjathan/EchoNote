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
    @ApplicationContext private val ctx: Context,
) :
    ViewModel() {
    val _voiceState = MutableStateFlow(VoiceState())
    val voiceState: StateFlow<VoiceState> = _voiceState.asStateFlow()


    fun updateFile(file: File, fileName: String) {
        _voiceState.update { current ->
            current.copy(file = file, fileName = fileName)
        }
    }

    fun startRecordingAudio() {
        val dir = File(ctx.filesDir, "EchoVoice")
        if (!dir.exists()) dir.mkdirs()
        val file = File(dir, "${System.currentTimeMillis()}.m4a")



        try {
            audioRecorder.start(file.absolutePath)
            updateFile(file, file.absolutePath)

        } catch (e: Exception) {
            Log.e("AudioSaved", "There was an error trying to record", e)
        }
    }

    fun stopRecordingAudio(): Boolean {
        try {
            audioRecorder.stop()

            return true
        } catch (e: Exception) {
            Log.e("AudioSaved", "There was an error trying to stop recording", e)
            return false
        }
    }

    fun playRecording() {
        if (_voiceState.value.file?.exists() == false) return
        try {
            audioPlayer.playFile(_voiceState.value.file!!)
        } catch (e: Exception) {
            Log.e("Recorded", "There was an error trying to play file", e)
        }
    }
}