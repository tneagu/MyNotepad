package com.tneagu.noteslist

import com.tneagu.domain.repositories.NotesRepo
import com.tneagu.noteslist.domain.usecases.GetNotesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class NotesListModule {

    @Provides
    fun provideNotesUseCase(notesRepo: NotesRepo): GetNotesUseCase =
        GetNotesUseCase(notesRepo)
}