package com.echonote.data.media

import java.io.File

interface AudioPlayer {
    fun playFile(file: File)
    fun stopPlayer()
}