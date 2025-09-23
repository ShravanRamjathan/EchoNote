package com.echonote.di

import android.content.Context
import com.echonote.data.media.AudioRecorder
import com.echonote.data.media.EchoPlayer
import com.echonote.data.media.EchoRecorder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class)
object EchoObjects {
@Provides
fun provideAudioRecorder(@ApplicationContext ctx: Context): EchoRecorder{
    return EchoRecorder(ctx)
}

    @Provides
    fun provideAudioPlayer(@ApplicationContext ctx: Context): EchoPlayer{
        return EchoPlayer(ctx)
    }
}