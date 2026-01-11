package com.example.notes.domain

import com.example.notes.domain.enums.SyncStatus

data class Note(
    val id: String,
    val title: String,
    val content: String,
    val date: Long,
    val updatedAt: Long,
    val syncStatus: SyncStatus
)
