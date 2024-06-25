package com.tneagu.domain.repositories

import com.tneagu.domain.entities.Note

interface NotesRepo {

    suspend fun getNotes(): List<Note>
    suspend fun getNote(noteId: String): Note

    suspend fun save(note: Note)
}