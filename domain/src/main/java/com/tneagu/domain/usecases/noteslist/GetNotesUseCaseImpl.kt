package com.tneagu.domain.usecases.noteslist

import com.tneagu.domain.repositories.NotesRepo
import com.tneagu.domain.entities.Note
import javax.inject.Inject

class GetNotesUseCaseImpl @Inject constructor(
    val notesRepo: NotesRepo
) : GetNotesUseCase {
    override suspend fun getNotes(): List<Note> = notesRepo.getNotes()
}