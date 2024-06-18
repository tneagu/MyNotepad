package com.tneagu.addnote.presentation

import androidx.lifecycle.ViewModel
import com.tneagu.addnote.presentation.model.AddNoteState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AddNoteViewModel @Inject constructor() : ViewModel() {

    private val _addNoteState = MutableStateFlow<AddNoteState>(AddNoteState.InitialState)
    val addNoteState = _addNoteState.asStateFlow()
}