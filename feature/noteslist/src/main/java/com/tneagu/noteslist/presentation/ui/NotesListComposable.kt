package com.tneagu.noteslist.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tneagu.domain.entities.Note

@Composable
fun NotesList(
    notes: List<Note>,
    onNoteClick: (note: Note) -> Unit,
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 8.dp)
    ) {
        items(notes) { note ->
            NoteCard(
                note = note,
                onClick = {
                    onNoteClick(note)
                },
            )
        }
    }
}

@Composable
@Preview
fun NotesListPreview() {
    NotesList(notes = listOf(
        Note(
            title = "Test note",
            body = "Lorem ipsum sic dolorum",
        ),
        Note(
            title = "Test note",
            body = "Lorem ipsum sic dolorum",
        ),
        Note(
            title = "Test note",
            body = "Lorem ipsum sic dolorum",
        ),
        Note(
            title = "Test note",
            body = "Lorem ipsum sic dolorum",
        ),
    ),
        onNoteClick = {}
    )
}