package com.example.zappynotes.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.zappynotes.domain.model.Note
import com.example.zappynotes.presentation.home.NoteEvent
import com.example.zappynotes.presentation.home.NoteState


@Composable
fun AddEditScreen(state: NoteState, noteId : Int , navController: NavHostController, onEvent: (NoteEvent) -> Unit) {

    var title by rememberSaveable { mutableStateOf("") }
    var selectedColor by rememberSaveable { mutableIntStateOf(Note.noteColor.first().hashCode()) }
    var showColorDialog by rememberSaveable { mutableStateOf(false) }
    var content by rememberSaveable { mutableStateOf("") }

//    if(state.selectedNote != null){
//        title = state.selectedNote.title
//        content = state.selectedNote.content
//        selectedColor = state.selectedNote.color
//    }

    LaunchedEffect(key1 = state.selectedNote) {
        state.selectedNote?.let { note ->
            title = note.title
            content = note.content
            selectedColor = note.color
        }
    }

    fun handleSaveClick() {
        if (noteId == -1) {
            onEvent(NoteEvent.AddNote)
        } else {
            onEvent(NoteEvent.SetNoteId(noteId))
            onEvent(NoteEvent.UpdateNote)
        }

    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp).background(color = Color(selectedColor))
    ) {
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") },
            modifier = Modifier.fillMaxWidth(),
            placeholder = {Text("Enter Title")}
        )

        Spacer(modifier = Modifier.height(12.dp))



        OutlinedTextField(
            value = content,
            onValueChange = { content = it },
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth(),
            maxLines = 5
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                showColorDialog = true
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Select Color")
        }

        if (showColorDialog) {
            AlertDialog(
                onDismissRequest = { showColorDialog = false },
                confirmButton = {},
                title = { Text("Pick a Color") },
                text = {
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Note.noteColor.forEach { color ->
                            Surface(
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(CircleShape)
                                    .background(color)
                                    .clickable {
                                        selectedColor = color.hashCode()
                                        showColorDialog = false
                                    },
                                color = color
                            ) {}
                        }
                    }
                }
            )
        }


        Button(
            onClick = {
                onEvent(NoteEvent.SetTitle(title))
                onEvent(NoteEvent.SetContent(content))
                onEvent(NoteEvent.SetColor(selectedColor))
                handleSaveClick()
                onEvent(NoteEvent.ShowHomeScreen)
                navController.navigateUp()
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Save")
        }
    }

}