package com.example.notes.data.local.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.notes.domain.enums.SyncStatus


@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey val id: String,
    val title: String,
    val content: String,
    val date: Long,
    val updatedAt: Long,
    val syncStatus: SyncStatus,
    val pendingDelete: Boolean = false
)