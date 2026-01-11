package com.example.notes.ui.presentation.viewmodels.viewnote

import androidx.lifecycle.viewModelScope
import com.example.notes.base.BaseMviViewModel
import com.example.notes.data.local.GetNoteByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewNoteViewModel @Inject constructor(
    private val getNoteByIdUseCase: GetNoteByIdUseCase,
) : BaseMviViewModel<ViewNoteContract.ViewNoteState, ViewNoteContract.ViewNoteIntent, ViewNoteContract.ViewNoteEffect>() {

    override fun initialState(): ViewNoteContract.ViewNoteState = ViewNoteContract.ViewNoteState()

    override suspend fun handleIntent(intent: ViewNoteContract.ViewNoteIntent) {
        when (intent) {
            is ViewNoteContract.ViewNoteIntent.LoadNote -> loadNoteById(intent.noteId)
        }
    }

    private fun loadNoteById(noteId: String) {
        viewModelScope.launch {
            getNoteByIdUseCase.invoke(noteId).collect { note ->
                setState {
                    copy(
                        note = note,
                        isLoading = false
                    )
                }
            }
        }
    }

}