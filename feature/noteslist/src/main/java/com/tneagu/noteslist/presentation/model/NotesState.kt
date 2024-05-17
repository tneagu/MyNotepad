package com.tneagu.noteslist.presentation.model

import com.tneagu.domain.entities.Note

sealed class NotesState {

    data object NotInitialized : NotesState()
    data object Loading : NotesState()

    data class Loaded(
        val notes: List<Note>
    ) : NotesState()

}