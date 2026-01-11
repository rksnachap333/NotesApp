package com.example.notes.ui.presentation.viewmodels.addnote

import androidx.compose.runtime.Immutable
import com.example.notes.base.UiEffect
import com.example.notes.base.UiIntent
import com.example.notes.base.UiState
import com.example.notes.domain.Note

object AddNoteContract {
    @Immutable
    data class AddNoteState(
        val isLoading: Boolean = true,
        val title: String = "",
        val content: String = "",
    ) : UiState

    // Intent - All possible user actions
    sealed interface AddNoteIntent : UiIntent {
        object SaveNote : AddNoteIntent
        object Reset : AddNoteIntent
        object Back : AddNoteIntent
        data class TitleChanged(val title: String) : AddNoteIntent
        data class ContentChanged(val content: String) : AddNoteIntent
    }

    // Effect - One-time side effects
    sealed class AddNoteEffect : UiEffect {

    }
}