package com.tneagu.auth.login.domain.usecases

import com.tneagu.auth.login.domain.repositories.AuthRepo
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepo: AuthRepo
) {
    suspend operator fun invoke(email: String, password: String): Boolean {
        return try{
            authRepo.login(email, password)
            true
        } catch (_ : Exception){
            false
        }
    }
}