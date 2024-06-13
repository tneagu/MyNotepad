package com.tneagu.auth.login.domain.usecases

import com.tneagu.auth.login.domain.model.User
import com.tneagu.auth.login.domain.repositories.AuthRepo
import javax.inject.Inject

class CurrentUserUseCase @Inject constructor(
    private val authRepo: AuthRepo
) {
    suspend operator fun invoke(): User? = authRepo.getCurrentUser()

}