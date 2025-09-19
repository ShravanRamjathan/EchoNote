package com.echonote.data

interface AudioRecorder {

    fun start(outPutFilePath:String)
    fun stop()
}