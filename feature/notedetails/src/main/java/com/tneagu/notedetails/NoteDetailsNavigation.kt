package com.tneagu.notedetails

import com.tneagu.appnavigation.NavigationAction

object NoteDetailsNavigation {

    const val noteDetailsRoute = "noteDetails"
    const val noteIdArg = "noteId"
    fun openNoteDetails(noteId: String) = object : NavigationAction {
        override val destination: String = "$noteDetailsRoute/$noteId"
    }
}