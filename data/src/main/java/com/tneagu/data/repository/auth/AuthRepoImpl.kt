package com.tneagu.data.repository.auth

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepoImpl @Inject constructor(
    val firebaseAuth: FirebaseAuth
) : com.tneagu.domain.repositories.AuthRepo {
    override suspend fun login(email: String, password: String): String {
        firebaseAuth.signInWithEmailAndPassword(email, password).await()

        val user = firebaseAuth.currentUser ?: throw FirebaseAuthException(
            "authError",
            "No authenticated user"
        )
        return user.uid
    }
}