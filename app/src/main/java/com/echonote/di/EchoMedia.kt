package com.echonote.di

import com.echonote.data.media.AudioPlayer
import com.echonote.data.media.AudioRecorder
import com.echonote.data.media.EchoPlayer
import com.echonote.data.media.EchoRecorder
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn

import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class EchoMedia {

    @Binds
    abstract  fun bindEchoRecorder(
        echoRecorder: EchoRecorder
    ): AudioRecorder

    @Binds
    abstract fun bindEchoPlayer(
        echoPlayer: EchoPlayer
    ): AudioPlayer


}