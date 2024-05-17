package com.tneagu.data.repository.notes

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.dataObjects
import com.tneagu.domain.entities.Note
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class NotesRepoImpl @Inject constructor(
    val fireStore: FirebaseFirestore,
    val auth: FirebaseAuth,
) : com.tneagu.domain.repositories.NotesRepo {
    override suspend fun getNotes(): List<com.tneagu.domain.entities.Note> {
        val userId = auth.currentUser?.uid
        return fireStore.collection(collectionName)
            .whereEqualTo("userId", userId)
            .dataObjects<com.tneagu.domain.entities.Note>()
            .first()
    }

    override suspend fun getNote(noteId: String): com.tneagu.domain.entities.Note {
        return fireStore.collection(collectionName).whereEqualTo("id", noteId)
            .dataObjects<com.tneagu.domain.entities.Note>().first().first()
    }

    override suspend fun save(note: com.tneagu.domain.entities.Note) {
        val collection = fireStore.collection(collectionName)
        collection.add(note)
    }

    companion object {
        private const val collectionName = "notes"
    }
}