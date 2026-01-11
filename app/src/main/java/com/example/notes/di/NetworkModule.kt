package com.example.notes.di

import com.example.notes.data.remote.FakeNoteRemoteDataSource
import com.example.notes.domain.remote.NoteRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideNoteRemoteDataSource(): NoteRemoteDataSource {
        return FakeNoteRemoteDataSource()
    }
}
