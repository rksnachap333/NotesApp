package com.example.notes.ui.presentation.viewmodels.viewnote

import androidx.compose.runtime.Immutable
import com.example.notes.base.UiEffect
import com.example.notes.base.UiIntent
import com.example.notes.base.UiState
import com.example.notes.domain.Note

object ViewNoteContract {
    @Immutable
    data class ViewNoteState(
        val isLoading: Boolean = true,
        val note: Note? = null
    ) : UiState

    // Intent - All possible user actions
    sealed interface ViewNoteIntent : UiIntent {
        data class LoadNote(val noteId: String) : ViewNoteIntent
    }

    // Effect - One-time side effects
    sealed class ViewNoteEffect : UiEffect {

    }
}