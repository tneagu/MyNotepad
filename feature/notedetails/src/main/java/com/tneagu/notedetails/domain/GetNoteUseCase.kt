package com.tneagu.notedetails.domain

import com.tneagu.data.repository.notes.model.Note

interface GetNoteUseCase {

    suspend fun getNote(noteId: String) : Note
}