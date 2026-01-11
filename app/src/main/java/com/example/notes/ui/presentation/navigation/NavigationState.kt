package com.example.notes.ui.presentation.navigation

sealed class NavigationState {
    object Loader : NavigationState()
    object Home : NavigationState()
    object AddNote : NavigationState()
    data class ViewNote(val id: String) : NavigationState()
}