package com.tneagu.noteslist.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tneagu.data.repository.notes.model.Note
import com.tneagu.noteslist.presentation.model.NotesState
import com.tneagu.noteslist.presentation.ui.NotesList

@Composable
fun NotesListScreen(
    state: NotesState,
    onNoteClick: (Note) -> Unit
) {

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        when (state) {
            is NotesState.Loaded -> {
                NotesList(
                    notes = state.notes,
                    onNoteClick = onNoteClick,
                )
            }

            NotesState.NotInitialized -> {}
            NotesState.Loading -> {}
        }
    }
}