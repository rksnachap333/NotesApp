package com.example.notes.data.dto

import com.example.notes.domain.enums.SyncStatus

data class NoteDto(
    val id: String,
    val title: String,
    val content: String,
    val date: Long,
    val updatedAt: Long,
    val syncStatus: SyncStatus
)