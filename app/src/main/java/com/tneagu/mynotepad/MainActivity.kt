package com.tneagu.mynotepad

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tneagu.appnavigation.AppNavigator
import com.tneagu.auth.AuthNavigation
import com.tneagu.auth.login.presentation.LoginScreen
import com.tneagu.auth.login.presentation.LoginViewModel
import com.tneagu.notedetails.NoteDetailsNavigation
import com.tneagu.notedetails.presentation.NoteDetailsScreen
import com.tneagu.notedetails.presentation.NoteDetailsViewModel
import com.tneagu.noteslist.NotesListNavigation
import com.tneagu.noteslist.presentation.NotesListScreen
import com.tneagu.noteslist.presentation.NotesListViewModel
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
        startDestination = AuthNavigation.loginRoute,
    ) {
        composable(AuthNavigation.loginRoute) {
            val viewModel = hiltViewModel<LoginViewModel>()
            LoginScreen(
                onLoginClick = viewModel::login
            )
        }


        composable(NotesListNavigation.notesListRoute) {
            val viewModel = hiltViewModel<NotesListViewModel>()
            val screenState = viewModel.notesState.collectAsState().value
            NotesListScreen(
                state = screenState,
                onNoteClick = {
                    viewModel.onNoteClick(it.id)
                }
            )
        }


        composable(
            route = "${NoteDetailsNavigation.noteDetailsRoute}/{${NoteDetailsNavigation.noteIdArg}}",
            arguments = listOf(navArgument(NoteDetailsNavigation.noteIdArg) {
                type = NavType.StringType
            }),
        ) {
            val noteId = it.arguments?.getString(NoteDetailsNavigation.noteIdArg) ?: ""

            val viewModel =
                hiltViewModel<NoteDetailsViewModel, NoteDetailsViewModel.NoteDetailsViewModelFactory>
                { factory ->
                    factory.create(noteId)
                }
            val screenState = viewModel.noteDetailsState.collectAsState().value
            NoteDetailsScreen(state = screenState)
        }
    }
}