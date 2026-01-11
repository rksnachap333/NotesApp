package com.example.notes.data.remote

import com.example.notes.domain.Note
import com.example.notes.domain.remote.NoteRemoteDataSource
import javax.inject.Inject

class FakeNoteRemoteDataSource @Inject constructor(
    
) : NoteRemoteDataSource {

    private val serverNotes = mutableMapOf<String, Note>()

    override suspend fun pushNotes(notes: List<Note>): List<Note> {
        notes.forEach { note ->
            val serverNote = serverNotes[note.id]
            if (serverNote == null || note.updatedAt > serverNote.updatedAt) {
                serverNotes[note.id] = note
            }
        }
        return serverNotes.values.toList()
    }

    override suspend fun fetchNotes(): List<Note> {
        return serverNotes.values.toList()
    }

    override suspend fun deleteNotes(ids: List<String>) {
        ids.forEach { id ->
            if (serverNotes.containsKey(id)) {
                serverNotes.remove(id)
            }
        }
    }
}
