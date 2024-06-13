package com.tneagu.notedetails.domain.usecases

import com.tneagu.domain.entities.Note
import com.tneagu.domain.repositories.NotesRepo
import javax.inject.Inject

class GetNoteDetailsUseCase @Inject constructor(
    private val notesRepo: NotesRepo
) {
    suspend operator fun invoke(noteId: String): Note = notesRepo.getNote(noteId)

}