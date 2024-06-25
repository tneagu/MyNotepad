package com.tneagu.data.repository.notes

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.dataObjects
import com.tneagu.domain.entities.Note
import com.tneagu.domain.repositories.NotesRepo
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class NotesRepoImpl @Inject constructor(
    val fireStore: FirebaseFirestore,
    val auth: FirebaseAuth,
) : NotesRepo {
    override suspend fun getNotes(): List<Note> {
        val userId = auth.currentUser?.uid
        return fireStore.collection(collectionName)
            .whereEqualTo("userId", userId)
            .dataObjects<Note>()
            .first()
    }

    override suspend fun getNote(noteId: String): Note {
        return fireStore.collection(collectionName).whereEqualTo("id", noteId)
            .dataObjects<Note>().first().first()
    }

    override suspend fun save(note: Note) {
        try {
            if(auth.currentUser == null){
                throw IllegalStateException("User must be authenticated")
            }
            val noteWithUserId = note.copy(userId = auth.currentUser!!.uid)
            val documentReference = fireStore.collection(collectionName)
                .add(noteWithUserId)
                .await()

            val noteWithId = noteWithUserId.copy(id = documentReference.id)
            documentReference.set(noteWithId).await()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    companion object {
        private const val collectionName = "notes"
    }
}