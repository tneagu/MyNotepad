package com.tneagu.domain

import com.tneagu.domain.repositories.AuthRepo
import com.tneagu.domain.repositories.NotesRepo
import com.tneagu.domain.usecases.login.LoginUseCase
import com.tneagu.domain.usecases.login.LoginUseCaseImpl
import com.tneagu.domain.usecases.notedetails.GetNoteDetailsUseCase
import com.tneagu.domain.usecases.notedetails.GetNoteDetailsUseCaseImpl
import com.tneagu.domain.usecases.noteslist.GetNotesUseCase
import com.tneagu.domain.usecases.noteslist.GetNotesUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object DomainModule {

    @Provides
    fun provideLoginUseCase(authRepo: AuthRepo): LoginUseCase = LoginUseCaseImpl(authRepo)

    @Provides
    fun provideNoteDetailsUseCase(notesRepo: NotesRepo): GetNoteDetailsUseCase =
        GetNoteDetailsUseCaseImpl(notesRepo)

    @Provides
    fun provideNotesUseCase(notesRepo: NotesRepo): GetNotesUseCase =
        GetNotesUseCaseImpl(notesRepo)
}