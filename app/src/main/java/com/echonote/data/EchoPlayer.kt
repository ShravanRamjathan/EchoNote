package com.echonote.data

import android.content.Context
import android.media.MediaPlayer
import android.util.Log
import androidx.core.net.toUri
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File

class EchoPlayer(@ApplicationContext private val ctx: Context) : AudioPlayer {
    var player: MediaPlayer? = null

    override fun playFile(file: File) {
       player = MediaPlayer().apply {
           try{
               setDataSource(file.absolutePath)
               prepare()
               start()
           }catch (e: Exception){
               Log.e("Recorded", "prepare failed", e)
           }
       }


    }

    override fun stopPlayer() {

       player?.stop()
        player?.release()
        player = null
    }

}