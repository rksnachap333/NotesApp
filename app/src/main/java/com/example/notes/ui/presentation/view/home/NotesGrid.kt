package com.example.notes.ui.presentation.view.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.notes.domain.Note
import com.example.notes.ui.presentation.viewmodels.addnote.AddNoteContract
import com.example.notes.ui.presentation.viewmodels.home.HomeContract

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NotesGrid(
    notes: List<Note>,
    modifier: Modifier = Modifier,
    onNoteClick: (Note) -> Unit,
    onSendIntent: (HomeContract.HomeIntent) -> Unit = {},
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(
            items = notes,
            key = { it.id }
        ) { note ->
            NoteGridItem(
                note = note,
                onClick = { onNoteClick(note) },
                onDeleteClick = {
                    onSendIntent(HomeContract.HomeIntent.DeleteNote(note.id))
                }
            )
        }
    }
}
