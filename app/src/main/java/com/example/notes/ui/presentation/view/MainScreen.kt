package com.example.notes.ui.presentation.view

import HomeScreen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.notes.ui.presentation.navigation.NavigationManager
import com.example.notes.ui.presentation.navigation.NavigationState
import com.example.notes.ui.presentation.view.addnote.AddNote
import com.example.notes.ui.presentation.view.loader.Loader
import com.example.notes.ui.presentation.view.viewnote.ViewNoteScreen
import com.example.notes.ui.presentation.viewmodels.addnote.AddNoteViewModel
import com.example.notes.ui.presentation.viewmodels.viewnote.ViewNoteContract
import com.example.notes.ui.presentation.viewmodels.viewnote.ViewNoteViewModel

@Composable
fun MainScreen(
    navigationManager: NavigationManager,
    addNoteViewModel: AddNoteViewModel = viewModel(),
) {
    val navigationState by navigationManager.currentState.collectAsState()
    val currentNavState = navigationState
    when (currentNavState) {
        is NavigationState.Loader -> {
            // Show Loader Screen
            Loader(
                navigationManager = navigationManager
            )
        }

        is NavigationState.Home -> {
            //Show Home Screen
            HomeScreen(
                navigationManager
            ) {
                navigationManager.navigateTo(NavigationState.AddNote)
            }
        }

        is NavigationState.AddNote -> {
            AddNote(
                onSendIntent = { intent ->
                    addNoteViewModel.sendIntent(intent)
                },
            )
        }

        is NavigationState.ViewNote -> {
            val noteId = currentNavState.id
            val viewModel: ViewNoteViewModel = viewModel()

            // tell the ViewModel to load the note
            LaunchedEffect(noteId) {
                viewModel.sendIntent(ViewNoteContract.ViewNoteIntent.LoadNote(noteId))
            }

            ViewNoteScreen(
                viewModel = viewModel,
                onBack = { navigationManager.navigateTo(NavigationState.Home) }
            )

        }


    }

}