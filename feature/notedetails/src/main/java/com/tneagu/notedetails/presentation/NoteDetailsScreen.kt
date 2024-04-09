package com.tneagu.notedetails.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tneagu.notedetails.presentation.model.NoteDetailsState
import com.tneagu.notedetails.presentation.ui.NoteDetails

@Composable
fun NoteDetailsScreen(state: NoteDetailsState) {

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        when (state) {
            is NoteDetailsState.Loaded -> {
                NoteDetails(note = state.note)
            }

            NoteDetailsState.NotInitialized -> {}
            NoteDetailsState.Loading -> {}
        }
    }
}