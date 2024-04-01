package com.tneagu.noteslist.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.tneagu.noteslist.R
import com.tneagu.noteslist.presentation.model.NotesState
import com.tneagu.noteslist.presentation.ui.NotesList
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotesFragment : Fragment() {

    private val viewModel by viewModels<NotesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    when (val notesState = viewModel.notesState.collectAsState().value) {
                        is NotesState.Loaded -> {
                            NotesList(
                                notes = notesState.notes,
                                onNoteClick = {

                                    findNavController().navigate(R.id.viewNoteDetails)
                                },
                            )
                        }

                        NotesState.NotInitialized -> {}
                        NotesState.Loading -> {}
                    }
                }

            }
        }
    }
}