package com.example.notes.domain.remote

import com.example.notes.domain.Note

interface NoteRemoteDataSource {
    suspend fun pushNotes(notes: List<Note>): List<Note>
    suspend fun fetchNotes(): List<Note>

    suspend fun deleteNotes(ids: List<String>)

}