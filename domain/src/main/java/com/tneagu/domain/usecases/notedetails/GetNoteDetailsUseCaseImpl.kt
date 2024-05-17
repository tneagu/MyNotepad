package com.tneagu.domain.usecases.notedetails

import com.tneagu.domain.repositories.NotesRepo
import com.tneagu.domain.entities.Note
import javax.inject.Inject

class GetNoteDetailsUseCaseImpl @Inject constructor(
    private val notesRepo: NotesRepo
): GetNoteDetailsUseCase {
    override suspend fun getNote(noteId: String): Note = notesRepo.getNote(noteId)

}