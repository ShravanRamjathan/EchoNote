package com.echonote.di

import com.echonote.data.AudioPlayer
import com.echonote.data.AudioRecorder
import com.echonote.data.EchoPlayer
import com.echonote.data.EchoRecorder
import dagger.Binds
import dagger.Module
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