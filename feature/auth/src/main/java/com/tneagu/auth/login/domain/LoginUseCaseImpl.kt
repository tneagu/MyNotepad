package com.tneagu.auth.login.domain

import com.tneagu.data.repository.auth.AuthRepo
import javax.inject.Inject

class LoginUseCaseImpl @Inject constructor(
    private val authRepo: AuthRepo
): LoginUseCase {
    override suspend fun login(email: String, password: String): Boolean {
        return try{
            authRepo.login(email, password)
            true
        } catch (_ : Exception){
            false
        }
    }
}