package com.tneagu.domain.usecases.login

import com.tneagu.domain.repositories.AuthRepo
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