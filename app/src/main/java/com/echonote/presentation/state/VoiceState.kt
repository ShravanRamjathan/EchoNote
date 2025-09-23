package com.echonote.presentation.state

import android.media.MediaRecorder
import java.io.File
import java.util.Timer
import kotlin.time.Duration
import kotlin.time.TimeSource

data class VoiceState(
   val fileName:String = "",
    val file: File? = null,
    val audioLength:Long = 0,
    val timer: TimeSource = TimeSource.Monotonic
)
