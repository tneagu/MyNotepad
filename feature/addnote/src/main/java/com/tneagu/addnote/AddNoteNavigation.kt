package com.tneagu.addnote

import com.tneagu.appnavigation.NavigationAction

object AddNoteNavigation {

    const val addNoteRoute = "addNote"

    fun openAddNote() = object : NavigationAction {
        override val destination: String = addNoteRoute
    }
}