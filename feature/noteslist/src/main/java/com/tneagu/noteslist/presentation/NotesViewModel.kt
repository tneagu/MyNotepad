package com.tneagu.noteslist.presentation

import androidx.lifecycle.ViewModel
import com.tneagu.noteslist.domain.GetNotesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    val getNotesUseCaseImpl: GetNotesUseCase
) : ViewModel() {

    val viewModelText = getNotesUseCaseImpl.getNotes().first()
}