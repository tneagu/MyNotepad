package com.tneagu.data.repository.notes

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.dataObjects
import com.tneagu.data.repository.notes.model.Note
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class NotesRepoImpl @Inject constructor(
    val fireStore: FirebaseFirestore
) : NotesRepo {
    override suspend fun getNotes(): List<Note> {
        return fireStore.collection(collectionName).dataObjects<Note>().first()
    }

    override suspend fun save(note: Note) {
        val collection = fireStore.collection(collectionName)
        collection.add(note)
    }

    companion object {
        private const val collectionName = "notes"
    }
}