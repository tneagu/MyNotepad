package com.tneagu.noteslist

import com.tneagu.noteslist.domain.GetNotesUseCase
import com.tneagu.noteslist.domain.GetNotesUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
class NotesModule {

    @Provides
    fun provideNotesUseCase(): GetNotesUseCase = GetNotesUseCaseImpl()
}