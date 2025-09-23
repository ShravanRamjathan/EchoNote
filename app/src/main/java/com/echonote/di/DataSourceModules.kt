package com.echonote.di

import com.echonote.data.notes.NotesDataSource
import com.echonote.data.notes.NotesRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.github.jan.supabase.SupabaseClient
import javax.inject.Singleton

@Module
@Singleton
class DataSourceModules {

    @Provides

    fun providesRemoteDataSource( supabaseClient: SupabaseClient): NotesDataSource{
        return NotesRemoteDataSource(supabaseClient)
    }
    @Module
    abstract  class  DataSource{
        @Binds
        abstract fun bindsRemoteDataSource(notesRemoteDataSource: NotesRemoteDataSource): NotesDataSource
    }
}