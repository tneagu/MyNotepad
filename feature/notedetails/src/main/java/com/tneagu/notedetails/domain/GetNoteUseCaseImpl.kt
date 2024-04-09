package com.tneagu.notedetails.domain

import com.tneagu.data.repository.notes.NotesRepo
import com.tneagu.data.repository.notes.model.Note
import javax.inject.Inject

class GetNoteUseCaseImpl @Inject constructor(
    private val notesRepo: NotesRepo
): GetNoteUseCase{
    override suspend fun getNote(noteId: String): Note  = notesRepo.getNote(noteId)

}