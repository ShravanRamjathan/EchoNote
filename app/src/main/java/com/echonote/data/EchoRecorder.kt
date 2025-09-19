package com.echonote.data

import android.content.Context
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import dagger.hilt.android.qualifiers.ApplicationContext


class EchoRecorder(@ApplicationContext private val ctx: Context) : AudioRecorder {
    private var recorder: MediaRecorder? = null


    private fun createRecorder(): MediaRecorder{
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.S){
           return MediaRecorder(ctx)
        }else{
         return   MediaRecorder()
        }
    }
    override fun start(outPutFilePath: String) {
        recorder = MediaRecorder(ctx)
            .apply {
                setAudioSource(MediaRecorder.AudioSource.MIC)
                setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
                setOutputFile(outPutFilePath)
                setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
                try {
                    prepare()
                } catch (e: Exception) {
                    Log.e("voiceTag", "prepare() failed", e)
                }
                start()
            }

    }

    override fun stop() {
        recorder?.apply {
            stop()
            release()
        }
    }
}