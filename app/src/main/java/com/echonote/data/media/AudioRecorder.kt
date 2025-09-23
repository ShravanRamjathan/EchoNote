package com.echonote.data.media

interface AudioRecorder {

    fun start(outPutFilePath:String)
    fun stop()
}