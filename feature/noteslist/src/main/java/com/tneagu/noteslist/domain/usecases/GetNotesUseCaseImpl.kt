package com.tneagu.noteslist.domain.usecases

import com.tneagu.domain.entities.Note
import com.tneagu.domain.repositories.NotesRepo
import javax.inject.Inject

class GetNotesUseCase @Inject constructor(
    val notesRepo: NotesRepo
) {
    suspend operator fun invoke(): List<Note> = notesRepo.getNotes()
}