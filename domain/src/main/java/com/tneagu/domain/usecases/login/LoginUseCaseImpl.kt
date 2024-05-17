package com.tneagu.domain.usecases.login

import com.tneagu.domain.repositories.AuthRepo
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