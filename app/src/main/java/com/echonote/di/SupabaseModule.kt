package com.echonote.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.status.SessionSource
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.storage.Storage
import io.github.jan.supabase.supabaseJson
import javax.inject.Singleton

@Module
@InstallIn(Singleton::class)
object SupabaseModule {

@Provides
    fun provideSupabaseClient(): SupabaseClient{
        val supabase = createSupabaseClient(
            supabaseUrl = "https://xyzcompany.supabase.co",
            supabaseKey = "your_public_anon_key",

        ) {

            install(Postgrest)
            install(Storage)
        }
        return supabase
    }


}