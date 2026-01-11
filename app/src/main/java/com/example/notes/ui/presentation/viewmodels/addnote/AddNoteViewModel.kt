package com.example.notes.ui.presentation.viewmodels.addnote

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.viewModelScope
import com.example.notes.base.BaseMviViewModel
import com.example.notes.data.local.AddNoteUseCase
import com.example.notes.domain.Note
import com.example.notes.domain.enums.SyncStatus
import com.example.notes.ui.presentation.navigation.NavigationManager
import com.example.notes.ui.presentation.navigation.NavigationState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class AddNoteViewModel @Inject constructor(
    private val addNoteUseCase: AddNoteUseCase,
    @ApplicationContext private val appContext: Context,
    private val navigationManager: NavigationManager
) : BaseMviViewModel<AddNoteContract.AddNoteState, AddNoteContract.AddNoteIntent, AddNoteContract.AddNoteEffect>() {
    override fun initialState(): AddNoteContract.AddNoteState = AddNoteContract.AddNoteState()


    override suspend fun handleIntent(intent: AddNoteContract.AddNoteIntent) {
        when (intent) {
            is AddNoteContract.AddNoteIntent.SaveNote -> saveNote()
            is AddNoteContract.AddNoteIntent.Back -> backToNotes()
            is AddNoteContract.AddNoteIntent.TitleChanged -> updateTitle(intent.title)
            is AddNoteContract.AddNoteIntent.ContentChanged -> updateContent(intent.content)
            is AddNoteContract.AddNoteIntent.Reset -> resetState()

        }
    }

    private fun saveNote() {
        val note = Note(
            id = UUID.randomUUID().toString(),
            title = state.value.title,
            content = state.value.content,
            date = System.currentTimeMillis(),
            updatedAt = System.currentTimeMillis(),
            syncStatus = SyncStatus.PENDING
        )
        viewModelScope.launch {
            addNoteUseCase.invoke(note)
        }
        Toast.makeText(appContext, "Notes Saved !!", Toast.LENGTH_SHORT).show()
    }

    private fun backToNotes() {
        navigationManager.navigateTo(NavigationState.Home)
    }


    private fun updateTitle(title: String) {
        setState { copy(title = title) }
    }

    private fun updateContent(content: String) {
        setState { copy(content = content) }
    }

    private fun resetState() {
        setState { copy(title = "", content = "") }
    }
}
