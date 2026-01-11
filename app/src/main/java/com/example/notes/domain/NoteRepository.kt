package com.example.notes.domain

import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    fun getAllNotes(): Flow<List<Note>>
    fun getNoteById(id: String): Flow<Note?>

    suspend fun deleteNote(id: String)
    suspend fun addNote(note: Note)

    suspend fun sync()
}