package com.tneagu.domain.repositories

import com.tneagu.domain.entities.Note

interface NotesRepo {

    suspend fun getNotes(): List<com.tneagu.domain.entities.Note>
    suspend fun getNote(noteId: String): com.tneagu.domain.entities.Note

    suspend fun save(note: com.tneagu.domain.entities.Note)
}