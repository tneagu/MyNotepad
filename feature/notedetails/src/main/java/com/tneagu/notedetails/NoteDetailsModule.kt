package com.tneagu.notedetails

import com.tneagu.domain.repositories.NotesRepo
import com.tneagu.notedetails.domain.usecases.GetNoteDetailsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class NoteDetailsModule {

    @Provides
    fun provideNoteDetailsUseCase(notesRepo: NotesRepo): GetNoteDetailsUseCase =
        GetNoteDetailsUseCase(notesRepo)
}