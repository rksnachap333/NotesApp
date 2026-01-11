package com.example.notes.data.local

import com.example.notes.domain.Note
import com.example.notes.domain.NoteRepository
import com.example.notes.domain.remote.SyncScheduler
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNotesUseCase @Inject constructor(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(): Flow<List<Note>> {
        return repository.getAllNotes()
    }
}

class AddNoteUseCase @Inject constructor(
    private val repository: NoteRepository,
    private val syncScheduler: SyncScheduler
) {
    suspend operator fun invoke(note: Note) {
        repository.addNote(note)
        syncScheduler.scheduleSync()
    }
}

class GetNoteByIdUseCase @Inject constructor(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(id: String): Flow<Note?> {
        return repository.getNoteById(id)
    }
}

class DeleteNoteUseCase @Inject constructor(
    private val repository: NoteRepository,
    private val syncScheduler: SyncScheduler
) {
    suspend operator fun invoke(id: String) {
        repository.deleteNote(id)
        syncScheduler.scheduleSync()
    }
}