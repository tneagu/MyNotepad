package com.tneagu.noteslist.domain

import com.tneagu.data.repository.notes.model.Note

interface GetNotesUseCase {

    suspend fun getNotes() : List<Note>
}