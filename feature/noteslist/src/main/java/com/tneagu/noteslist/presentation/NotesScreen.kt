package com.tneagu.noteslist.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tneagu.noteslist.presentation.model.NotesState
import com.tneagu.noteslist.presentation.ui.NotesList

@Composable
fun NotesScreen(
    viewModel: NotesViewModel,
    onNoteClick: (String) -> Unit,
) {

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        when (val notesState = viewModel.notesState.collectAsState().value) {
            is NotesState.Loaded -> {
                NotesList(
                    notes = notesState.notes,
                    onNoteClick = {

                        viewModel.onNoteClick(it.body)
                    },
                )
            }

            NotesState.NotInitialized -> {}
            NotesState.Loading -> {}
        }
    }
}