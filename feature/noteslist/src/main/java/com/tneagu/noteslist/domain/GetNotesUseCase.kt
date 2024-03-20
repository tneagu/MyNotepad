package com.tneagu.noteslist.domain

interface GetNotesUseCase {

    fun getNotes() : List<String>
}