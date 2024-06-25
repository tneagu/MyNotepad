package com.tneagu.mynotepad

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tneagu.addnote.AddNoteNavigation
import com.tneagu.addnote.presentation.AddNoteScreen
import com.tneagu.addnote.presentation.AddNoteViewModel
import com.tneagu.appnavigation.AppNavigator
import com.tneagu.appnavigation.BACK_NAVIGATION
import com.tneagu.auth.AuthNavigation
import com.tneagu.auth.login.presentation.LoginScreen
import com.tneagu.auth.login.presentation.LoginViewModel
import com.tneagu.auth.login.presentation.model.LoginState
import com.tneagu.notedetails.NoteDetailsNavigation
import com.tneagu.notedetails.presentation.NoteDetailsScreen
import com.tneagu.notedetails.presentation.NoteDetailsViewModel
import com.tneagu.noteslist.NotesListNavigation
import com.tneagu.noteslist.presentation.NotesListScreen
import com.tneagu.noteslist.presentation.NotesListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
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
            if (it.destination == BACK_NAVIGATION) {
                navController.popBackStack()
            } else {
                it.parcelableArguments.forEach { arg ->
                    navController.currentBackStackEntry?.arguments?.putParcelable(
                        arg.key,
                        arg.value
                    )
                }
                navController.navigate(it.destination, it.navOptions)
            }
        }
    }

    NavHost(
        navController = navController,
        startDestination = AuthNavigation.loginRoute,
    ) {
        composable(AuthNavigation.loginRoute) {
            val viewModel = hiltViewModel<LoginViewModel>()
            when (val screenState = viewModel.state.collectAsState().value) {
                LoginState.Error,
                LoginState.Loading,
                LoginState.NotAuthenticated -> {
                    LoginScreen(
                        state = screenState,
                        onLoginClick = viewModel::login
                    )
                }

                LoginState.LoggedIn -> {
                    //navigation is done in view model
                }

                LoginState.Uninitialized -> {
                    //na
                }
            }

        }


        composable(NotesListNavigation.notesListRoute) {
            val viewModel = hiltViewModel<NotesListViewModel>()
            val screenState = viewModel.notesState.collectAsState().value
            NotesListScreen(
                state = screenState,
                onNoteClick = {
                    viewModel.onNoteClick(it.id)
                },
                onAddNoteClick = {
                    viewModel.onAddNoteClicked()
                },
            )

            // load data each time page loads
            val coroutineScope = rememberCoroutineScope()
            LaunchedEffect(navController) {
                navController.addOnDestinationChangedListener { _, _, _ ->
                    if (navController.currentBackStackEntry?.destination?.route == NotesListNavigation.notesListRoute) {
                        coroutineScope.launch {
                            viewModel.loadData()
                        }
                    }
                }
            }
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

        composable(AddNoteNavigation.addNoteRoute) {
            val viewModel = hiltViewModel<AddNoteViewModel>()
            val screenState = viewModel.addNoteState.collectAsState().value
            AddNoteScreen(
                state = screenState,
                onAddNote = { title, content ->
                    viewModel.saveNote(title, content)
                },
            )
        }
    }
}