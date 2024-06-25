package com.tneagu.addnote.domain.usecase

import com.tneagu.domain.entities.Note
import com.tneagu.domain.repositories.NotesRepo
import javax.inject.Inject

class AddNoteUseCase @Inject constructor(private val noteRepository: NotesRepo) {

    suspend fun invoke(note: Note) {
        noteRepository.save(note)
    }
}