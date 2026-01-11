/*!
* \file BaseMviViewModel.kt
* \brief This file contains the BaseMviViewModel class which serves as
* a base class for implementing the MVI (Model-View-Intent) architecture
* pattern in Android ViewModels.
*
* Sorenson Communications Inc. Confidential. --  Do not distribute
* Copyright 2025 Sorenson Communications, Inc. -- All rights reserved
*/
package com.example.notes.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class BaseMviViewModel<State : UiState, Intent : UiIntent, Effect : UiEffect> : ViewModel() {

    // Initial state - must be provided by subclass
    abstract fun initialState(): State

    // State - represents current UI state
    private val _state = MutableStateFlow(initialState())
    val state: StateFlow<State> = _state.asStateFlow()

    // Intent - receives user actions (Channel is better than SharedFlow for one-time actions)
    private val _intent = Channel<Intent>(Channel.UNLIMITED)

    // Effect - one-time side effects (navigation, toasts, etc.)
    private val _effect = Channel<Effect>(Channel.UNLIMITED)
    val effect = _effect.receiveAsFlow()

    init {
        subscribeToIntents()
    }

    /**
     * Subscribe to intents from the UI
     */
    private fun subscribeToIntents() {
        viewModelScope.launch {
            _intent.receiveAsFlow().collect { intent ->
                handleIntent(intent)
            }
        }
    }

    /**
     * Send intent from UI (non-suspending for better UI responsiveness)
     */
    fun sendIntent(intent: Intent) {
        _intent.trySend(intent)
    }

    /**
     * Handle intent - must be implemented by subclass
     */
    protected abstract suspend fun handleIntent(intent: Intent)

    /**
     * Update state (thread-safe)
     */
    protected fun setState(reducer: State.() -> State) {
        _state.value = _state.value.reducer()
    }

    /**
     * Send one-time effect (non-suspending for better performance)
     */
    protected fun sendEffect(effect: Effect) {
        _effect.trySend(effect)
    }

    /**
     * Get current state value
     */
    protected fun currentState(): State = _state.value
}

/**
 * Marker interface for UI State
 */
interface UiState

/**
 * Marker interface for User Intents (Actions)
 */
interface UiIntent

/**
 * Marker interface for Side Effects
 */
interface UiEffect

