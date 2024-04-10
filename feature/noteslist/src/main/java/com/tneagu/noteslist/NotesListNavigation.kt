package com.tneagu.noteslist

import com.tneagu.appnavigation.NavigationAction
import com.tneagu.notedetails.NoteDetailsNavigation

object NotesListNavigation {

    const val notesListRoute = "notesList"

    fun openNoteList() = object : NavigationAction {
        override val destination: String = notesListRoute
    }
}