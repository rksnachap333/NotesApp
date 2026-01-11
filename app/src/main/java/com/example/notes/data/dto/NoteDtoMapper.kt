package com.example.notes.data.dto

import com.example.notes.data.local.db.NoteEntity
import com.example.notes.domain.Note


fun NoteEntity.toDomain(): Note =
    Note(id = id, title = title, content = content, date = date, updatedAt = updatedAt, syncStatus = syncStatus)

fun Note.toEntity(): NoteEntity =
    NoteEntity(
        id = id,
        title = title,
        content = content,
        date = date,
        updatedAt = updatedAt,
        syncStatus = syncStatus
    )

fun NoteDto.toDomain(): Note =
    Note(id = id, title = title, content = content, date = date, updatedAt = updatedAt, syncStatus = syncStatus)
