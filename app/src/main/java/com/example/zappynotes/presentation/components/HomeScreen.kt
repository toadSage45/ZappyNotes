package com.example.zappynotes.presentation.components


import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.zappynotes.domain.model.Note
import com.example.zappynotes.presentation.home.NoteEvent
import com.example.zappynotes.presentation.home.NoteState


@Composable
fun HomeScreen(
    state: NoteState,
    onEvent: (NoteEvent) -> Unit,
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    val notes = state.notes

    Scaffold(
        modifier = modifier.background(color = Color.White),
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("add_edit_note?noteId=${-1}") }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Note"
                )
            }
        }
    ) {

        fun onNoteClick(note: Note){
            navController.navigate(
                "add_edit_note?noteId=${note.id}"
            )
        }

        fun onDeleteClick(note: Note){
            onEvent(NoteEvent.DeleteNote(note))
        }
        Box(modifier = Modifier
            .background(color = Color.White)
            .fillMaxSize()){
            Text(
                text = "My Notes",
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                color = Color.Blue

            )
            LazyColumn(
                contentPadding = it,
                modifier = modifier
            ) {
                items(notes) { note->
                    NoteItem(
                        note = note,
                        modifier = Modifier,
                        onEditClick = { onNoteClick(note) },
                        onDeleteClick = {
                            onDeleteClick(note)
                        }
                    )
                }

            }
        }
    }
}

@Composable
fun NoteItem(note: Note, onEditClick: () -> Unit, onDeleteClick: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color.LightGray)
            .padding(8.dp)
    ) {
        Text(text = note.title, style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = note.content, style = MaterialTheme.typography.bodyMedium)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = onEditClick) {
                Text("Edit")
            }
            Button(onClick = onDeleteClick) {
                Text("Delete")
            }
        }
    }
}