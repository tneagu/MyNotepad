package com.tneagu.noteslist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tneagu.noteslist.domain.GetNotesUseCase
import com.tneagu.noteslist.presentation.model.NotesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    val getNotesUseCaseImpl: GetNotesUseCase
) : ViewModel() {

    private val _notesState = MutableStateFlow<NotesState>(NotesState.NotInitialized)
    val notesState = _notesState.asStateFlow()

    init {
        viewModelScope.launch {
            val notes = getNotesUseCaseImpl.getNotes()
            _notesState.value = NotesState.Loaded(
                notes = notes
            )
        }

    }
}