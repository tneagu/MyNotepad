package com.tneagu.addnote.presentation.model

sealed class AddNoteState {

    data object InitialState : AddNoteState()
    data object Loading : AddNoteState()
}