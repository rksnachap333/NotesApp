package com.example.notes.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.notes.domain.enums.SyncStatus
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Query("SELECT * FROM notes WHERE pendingDelete = 0 ORDER BY updatedAt DESC")
    fun observeNotes(): Flow<List<NoteEntity>>

    @Query("SELECT * FROM notes WHERE syncStatus != :status AND pendingDelete = 0 ")
    suspend fun getUnsyncedNotes(
        status: SyncStatus = SyncStatus.SYNCED
    ): List<NoteEntity>

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun upsert(note: NoteEntity)

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun upsertAll(notes: List<NoteEntity>)

    @Query("SELECT * FROM notes WHERE id = :noteId AND pendingDelete = 0 LIMIT 1")
    fun getNoteById(noteId: String): Flow<NoteEntity?>

    @Query("UPDATE notes SET pendingDelete = 1, syncStatus = :status WHERE id = :noteId")
    suspend fun markNotePendingDelete(noteId: String, status: SyncStatus = SyncStatus.PENDING)

    @Query("SELECT * FROM notes WHERE pendingDelete = 1")
    suspend fun getPendingDeletes(): List<NoteEntity>

    @Query("DELETE FROM notes WHERE id = :noteId")
    suspend fun deleteNoteById(noteId: String)
}