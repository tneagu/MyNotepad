package com.tneagu.notedetails.presentation.model

import com.tneagu.data.repository.notes.model.Note

sealed class NoteDetailsState {

    data object NotInitialized : NoteDetailsState()
    data object Loading : NoteDetailsState()

    data class Loaded(
        val note: Note
    ) : NoteDetailsState()
}