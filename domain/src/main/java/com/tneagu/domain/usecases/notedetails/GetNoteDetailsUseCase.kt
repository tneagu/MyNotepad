package com.tneagu.domain.usecases.notedetails

import com.tneagu.domain.entities.Note

interface GetNoteDetailsUseCase {

    suspend fun getNote(noteId: String) : Note
}