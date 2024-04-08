package com.tneagu.mynotepad

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tneagu.appnavigation.AppNavigator
import com.tneagu.notedetails.NoteDetailsNavigation
import com.tneagu.notedetails.ui.NoteDetailsScreen
import com.tneagu.noteslist.NotesListNavigation
import com.tneagu.noteslist.presentation.NotesScreen
import com.tneagu.noteslist.presentation.NotesViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var appNavigator: AppNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavHost(
                navController = rememberNavController(),
                appNavigator = appNavigator,
            )
        }
    }
}

@Composable
fun AppNavHost(
    navController: NavHostController,
    appNavigator: AppNavigator
) {

    appNavigator.navActions.collectAsStateWithLifecycle().value.also { navigationAction ->
        navigationAction?.let {
            it.parcelableArguments.forEach { arg ->
                navController.currentBackStackEntry?.arguments?.putParcelable(arg.key, arg.value)
            }
            navController.navigate(it.destination, it.navOptions)
        }
    }

    NavHost(
        navController = navController,
        startDestination = NotesListNavigation.notesListRoute,
    ) {

        composable(NotesListNavigation.notesListRoute) {
            val viewModel = hiltViewModel<NotesViewModel>()
            NotesScreen(viewModel = viewModel, onNoteClick = {
            })
        }


        composable(
            route = "${NoteDetailsNavigation.noteDetailsRoute}/{${NoteDetailsNavigation.noteIdArg}}",
            arguments = listOf(navArgument(NoteDetailsNavigation.noteIdArg) {
                type = NavType.StringType
            }),
        ) {
            NoteDetailsScreen()
        }
    }
}