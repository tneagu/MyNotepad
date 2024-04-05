package com.tneagu.mynotepad

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tneagu.notedetails.ui.NoteDetailsScreen
import com.tneagu.noteslist.presentation.NotesScreen
import com.tneagu.noteslist.presentation.NotesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavHost(navController = rememberNavController())
        }
    }
}

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "notesList",
    ) {
        composable("notesList") {
            val viewModel = hiltViewModel<NotesViewModel>()
            NotesScreen(viewModel = viewModel, onNoteClick = {
                navController.navigate("noteDetails")
            })
        }

        composable("noteDetails") {
            NoteDetailsScreen()
        }
    }
}