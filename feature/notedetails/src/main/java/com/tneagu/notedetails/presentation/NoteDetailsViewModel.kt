package com.tneagu.notedetails.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tneagu.domain.usecases.notedetails.GetNoteDetailsUseCase
import com.tneagu.notedetails.presentation.model.NoteDetailsState
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = NoteDetailsViewModel.NoteDetailsViewModelFactory::class)
class NoteDetailsViewModel @AssistedInject constructor(
    @Assisted val noteId: String,
    val noteUseCase: GetNoteDetailsUseCase,
) : ViewModel() {

    private val _noteDetailsState =
        MutableStateFlow<NoteDetailsState>(NoteDetailsState.NotInitialized)
    val noteDetailsState = _noteDetailsState.asStateFlow()

    init {
        viewModelScope.launch {
            val note = noteUseCase(noteId)
            _noteDetailsState.value = NoteDetailsState.Loaded(
                note = note
            )
        }

    }

    @AssistedFactory
    interface NoteDetailsViewModelFactory {
        fun create(noteId: String): NoteDetailsViewModel
    }
}