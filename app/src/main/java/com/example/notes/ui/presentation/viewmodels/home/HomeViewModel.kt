package com.example.notes.ui.presentation.viewmodels.home

import androidx.lifecycle.viewModelScope
import com.example.notes.base.BaseMviViewModel
import com.example.notes.domain.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: NoteRepository
) : BaseMviViewModel<HomeContract.HomeState, HomeContract.HomeIntent, HomeContract.HomeEffect>() {

    companion object {
        const val TAG = "HomeViewModel"
    }

    override fun initialState(): HomeContract.HomeState = HomeContract.HomeState()

    override suspend fun handleIntent(intent: HomeContract.HomeIntent) {
        when (intent) {
            is HomeContract.HomeIntent.DeleteNote -> deleteNote(intent.id)
//            is HomeContract.HomeIntent.EditNote -> editNote(intent.id)
        }
    }

    init {
        observeNotes()
    }

    private fun observeNotes() {
        viewModelScope.launch {
            repository.getAllNotes()
                .collectLatest { notes ->
                    println("$TAG: Notes loaded..${notes.size}")

                    setState {
                        copy(
                            isLoading = false,
                            notes = notes
                        )
                    }
                }
        }
    }

    private fun deleteNote(id: String) {
        viewModelScope.launch {
            repository.deleteNote(id)
        }
    }

    private fun editNote(id: String) {

    }

}