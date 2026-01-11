package com.example.notes.ui.presentation.viewmodels.home

import androidx.compose.runtime.Immutable
import com.example.notes.base.UiEffect
import com.example.notes.base.UiIntent
import com.example.notes.base.UiState
import com.example.notes.domain.Note

object HomeContract {

    @Immutable
    data class HomeState(
        val isLoading: Boolean = true,
        val notes: List<Note> = emptyList(),
    ) : UiState

    // Intent - All possible user actions
    sealed interface HomeIntent : UiIntent {
        data class DeleteNote(val id: String) : HomeIntent
    }

    // Effect - One-time side effects
    sealed class HomeEffect : UiEffect {

    }
}
