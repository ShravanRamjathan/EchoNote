package com.echonote.di

import com.echonote.data.notes.NotesDataSource
import com.echonote.data.notes.NotesRemoteDataSource
import com.echonote.data.notes.NotesRepository
import com.echonote.data.notes.NotesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModules {

    @Provides
    fun providesRemoteDataSource( supabaseClient: SupabaseClient): NotesDataSource{
        return NotesRemoteDataSource(supabaseClient)
    }

    @Provides
    fun providesNotesRepository( notesDataSource: NotesDataSource): NotesRepository{
        return NotesRepositoryImpl(notesDataSource)
    }

}
@Module
@InstallIn(ServiceComponent::class)
abstract  class  DataSource{
    @Binds
    abstract fun bindsRemoteDataSource(notesRemoteDataSource: NotesRemoteDataSource): NotesDataSource

    @Binds
    abstract fun bindsNoteRepository(notesRepositoryImpl: NotesRepositoryImpl): NotesRepository
}