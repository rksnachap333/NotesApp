package com.example.notes.di

import android.content.Context
import androidx.room.Room
import com.example.notes.data.local.db.NoteDao
import com.example.notes.data.local.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "note_db"
        ).build()

    @Provides
    fun provideNoteDao(db: AppDatabase): NoteDao =
        db.noteDao()
}
