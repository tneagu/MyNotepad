package com.tneagu.domain

import com.tneagu.domain.repositories.AuthRepo
import com.tneagu.domain.repositories.NotesRepo
import com.tneagu.domain.usecases.login.LoginUseCase
import com.tneagu.domain.usecases.notedetails.GetNoteDetailsUseCase
import com.tneagu.domain.usecases.noteslist.GetNotesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object DomainModule {

    @Provides
    fun provideLoginUseCase(authRepo: AuthRepo): LoginUseCase = LoginUseCase(authRepo)

    @Provides
    fun provideNoteDetailsUseCase(notesRepo: NotesRepo): GetNoteDetailsUseCase =
        GetNoteDetailsUseCase(notesRepo)

    @Provides
    fun provideNotesUseCase(notesRepo: NotesRepo): GetNotesUseCase =
        GetNotesUseCase(notesRepo)
}