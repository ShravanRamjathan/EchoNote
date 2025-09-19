package com.echonote.data

import java.io.File

interface AudioPlayer {
    fun playFile(file: File)
    fun stopPlayer()
}