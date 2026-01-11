package com.example.notes.data.local

import com.example.notes.data.dto.toDomain
import com.example.notes.data.dto.toEntity
import com.example.notes.data.local.db.NoteDao
import com.example.notes.di.IoDispatcher
import com.example.notes.domain.Note
import com.example.notes.domain.NoteRepository
import com.example.notes.domain.enums.SyncStatus
import com.example.notes.domain.remote.NoteRemoteDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject


class NoteRepositoryImpl @Inject constructor(
    private val dao: NoteDao,
    private val api: NoteRemoteDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : NoteRepository {

    override fun getAllNotes(): Flow<List<Note>> {
        return dao.observeNotes()
            .map { entities ->
                entities.map { entity ->
                    entity.toDomain()
                }
            }
            .flowOn(Dispatchers.IO)
    }

    override fun getNoteById(id: String): Flow<Note?> {
        return dao.getNoteById(id)
            .map { entity -> entity?.toDomain() }
            .flowOn(Dispatchers.IO)
    }

    override suspend fun deleteNote(id: String) {
        dao.markNotePendingDelete(id)
    }

    override suspend fun addNote(note: Note) {
        dao.upsert(note.toEntity())
    }

    override suspend fun sync() = coroutineScope {
        syncDeleteNotes()
        syncAddNotes()
    }

    private suspend fun syncDeleteNotes() {
        val pendingDeletes = dao.getPendingDeletes()

        if (pendingDeletes.isNotEmpty()) {
            api.deleteNotes(pendingDeletes.map { it.id })

            pendingDeletes.forEach {
                dao.deleteNoteById(it.id)
            }
        }
    }

    private suspend fun syncAddNotes() {
        val pendingEntities = dao.getUnsyncedNotes()

        if (pendingEntities.isEmpty()) return

        val pendingNotes = pendingEntities.map { it.toDomain() }
        api.pushNotes(pendingNotes)

        pendingEntities.forEach { entity ->
            dao.upsert(
                entity.copy(syncStatus = SyncStatus.SYNCED)
            )
        }
    }


}
