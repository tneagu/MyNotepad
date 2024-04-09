package com.tneagu.data.repository.notes

import com.tneagu.data.repository.notes.model.Note

interface NotesRepo {

    suspend fun getNotes(): List<Note>
    suspend fun getNote(noteId: String): Note

    suspend fun save(note: Note)
}