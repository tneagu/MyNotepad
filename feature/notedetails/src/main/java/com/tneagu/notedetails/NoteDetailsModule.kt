package com.tneagu.notedetails

import com.tneagu.data.repository.notes.NotesRepo
import com.tneagu.notedetails.domain.GetNoteUseCase
import com.tneagu.notedetails.domain.GetNoteUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
object NoteDetailsModule {

    @Provides
    fun provideNoteUseCase(notesRepo: NotesRepo): GetNoteUseCase = GetNoteUseCaseImpl(notesRepo)
}