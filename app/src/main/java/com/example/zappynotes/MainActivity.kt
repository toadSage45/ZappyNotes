package com.example.zappynotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.zappynotes.data.local.NoteDatabase
import com.example.zappynotes.navigation.NavGraph
import com.example.zappynotes.presentation.home.NoteViewModel
import com.example.zappynotes.ui.theme.ZappyNotesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ZappyNotesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val applicationContext = LocalContext.current
                    val db = Room.databaseBuilder(
                        applicationContext ,
                        NoteDatabase::class.java ,
                        "notes.db"
                    ).build()

                    val dao = db.dao

                    val navController = rememberNavController()
                    val viewModel = NoteViewModel(dao)
                    NavGraph(navController = navController,
                        viewModel = viewModel ,
                        modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

