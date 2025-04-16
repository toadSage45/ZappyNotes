package com.example.zappynotes.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.zappynotes.presentation.components.AddEditScreen
import com.example.zappynotes.presentation.components.HomeScreen
import com.example.zappynotes.presentation.home.NoteViewModel



@Composable
fun NavGraph(navController : NavHostController , viewModel : NoteViewModel , modifier : Modifier) {

    val state = viewModel.state.collectAsState().value

    NavHost(
        navController = navController ,
        startDestination = Screen.Home.route
    ){
        composable(route = Screen.Home.route){
            HomeScreen(state = state , onEvent = viewModel::onEvent , modifier = modifier , navController = navController)
        }

        composable(route = Screen.AddEditNote.route + "?noteId={noteId}",
            arguments = listOf(
                navArgument(name = "noteId"){
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
            ){backStackEntry ->
            val noteId = backStackEntry.arguments?.getInt("noteId") ?: -1


            AddEditScreen(navController = navController ,
                noteId = noteId,
                onEvent = viewModel::onEvent ,
                state = state
                )
        }

    }
}