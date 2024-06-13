package com.tneagu.data.repository.auth

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.tneagu.auth.login.domain.model.User
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepoImpl @Inject constructor(
    val firebaseAuth: FirebaseAuth
) : com.tneagu.auth.login.domain.repositories.AuthRepo {
    override suspend fun login(email: String, password: String): String {
        firebaseAuth.signInWithEmailAndPassword(email, password).await()

        val user = firebaseAuth.currentUser ?: throw FirebaseAuthException(
            "authError",
            "No authenticated user"
        )
        return user.uid
    }

    override suspend fun getCurrentUser(): User? =
        firebaseAuth.currentUser?.let {
            User(email = it.email ?: "")
        }

}