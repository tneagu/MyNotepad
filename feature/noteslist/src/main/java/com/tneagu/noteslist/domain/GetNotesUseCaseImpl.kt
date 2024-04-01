package com.tneagu.noteslist.domain

import com.tneagu.data.repository.notes.NotesRepo
import com.tneagu.data.repository.notes.model.Note
import javax.inject.Inject

class GetNotesUseCaseImpl @Inject constructor(
    val notesRepo: NotesRepo
) : GetNotesUseCase {
    override suspend fun getNotes(): List<Note> = notesRepo.getNotes()
}