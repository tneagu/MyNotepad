package com.tneagu.noteslist.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.tneagu.data.repository.notes.model.Note

@Composable
fun NotesList(
    notes: List<Note>,
    onNoteClick: (note: Note) -> Unit,
) {
    LazyColumn {
        items(notes) { note ->
            NoteCard(
                note = note,
                modifier = Modifier.clickable {
                    onNoteClick(note)
                },
            )
        }
    }
}