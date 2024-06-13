package com.tneagu.auth.login.domain.repositories

import com.tneagu.auth.login.domain.model.User

interface AuthRepo {

    suspend fun login(email: String, password: String): String

    suspend fun getCurrentUser(): User?
}