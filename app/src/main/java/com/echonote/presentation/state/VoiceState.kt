package com.echonote.presentation.state

import android.media.MediaRecorder
import java.io.File

data class VoiceState(
   val fileName:String = "",
    val file: File? = null,
    val audioLength:Long = 0

)
