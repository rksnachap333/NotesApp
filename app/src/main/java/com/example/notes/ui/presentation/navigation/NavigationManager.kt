package com.example.notes.ui.presentation.navigation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NavigationManager @Inject constructor() {

    private val _currentState = MutableStateFlow<NavigationState>(NavigationState.Loader)
    val currentState: StateFlow<NavigationState> = _currentState.asStateFlow()

    init {
        CoroutineScope(Dispatchers.Main).launch {
            _currentState.value = determineInitialState()
        }
    }

    private suspend fun determineInitialState(): NavigationState {
        //TODO run loader for 3 seconds and go to home
        return NavigationState.Loader
    }


    fun navigateTo(destination: NavigationState) {
        _currentState.value = destination
    }

}