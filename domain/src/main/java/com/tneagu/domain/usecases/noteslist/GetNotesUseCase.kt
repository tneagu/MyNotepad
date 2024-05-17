package com.tneagu.domain.usecases.noteslist

import com.tneagu.domain.entities.Note

interface GetNotesUseCase {

    suspend fun getNotes() : List<Note>
}