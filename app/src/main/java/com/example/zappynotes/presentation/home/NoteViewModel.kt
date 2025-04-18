package com.example.zappynotes.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zappynotes.di.Graph
import com.example.zappynotes.domain.model.Note
import com.example.zappynotes.domain.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NoteViewModel(private val noteRepository: NoteRepository = Graph.noteRepository) : ViewModel() {

    private val _notes = noteRepository.getNotes()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            emptyList()
        )

    private val _state = MutableStateFlow(NoteState())
    val state = combine(_state, _notes) { state, notes ->
        state.copy(
            notes = notes,
        )
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        NoteState()
    )

    fun onEvent(event: NoteEvent) {
        when (event) {
            is NoteEvent.DeleteNote -> {
                viewModelScope.launch {
                    noteRepository.deleteNote(event.note)
                }

            }
            is NoteEvent.SetNoteId -> {
                _state.update {
                    it.copy(
                        noteId = event.id
                    )
                }
            }

            is NoteEvent.AddNote -> {
                val title = _state.value.title
                val content = _state.value.content
                val color = _state.value.color
                if (title.isBlank() || content.isBlank()) {
                    return
                }
                viewModelScope.launch(Dispatchers.IO) {
                    noteRepository.addNote(
                        Note(
                            title = title,
                            content = content,
                            color = color,
                            timestamp = System.currentTimeMillis()
                        )
                    )

                    _state.update {
                        it.copy(
                            noteId = -1,
                            title = "",
                            content = "",
                            color = Note.noteColor.first().hashCode(),
                            selectedNote = null
                        )
                    }
                }
            }


            is NoteEvent.SetColor -> {
                _state.update {
                    it.copy(
                        color = event.color
                    )
                }
            }

            is NoteEvent.SetContent -> {
                _state.update {
                    it.copy(
                        content = event.content
                    )
                }
            }

            is NoteEvent.SetTitle -> {
                _state.update {
                    it.copy(
                        title = event.title
                    )
                }
            }

            is NoteEvent.GetNoteById -> {
                viewModelScope.launch {
                    val note = noteRepository.getNoteById(event.id)
                    _state.update {
                        it.copy(
                            selectedNote = note
                        )
                    }
                }
            }

            is NoteEvent.UpdateNote -> {
                val id = _state.value.noteId
                val title = _state.value.title
                val content = _state.value.content
                val color = _state.value.color
                if (title.isBlank() || content.isBlank()) {
                    return
                }

                viewModelScope.launch(Dispatchers.IO) {
                    noteRepository.updateNote(
                        Note(
                            id = id,
                            title = title,
                            content = content,
                            color = color,
                            timestamp = System.currentTimeMillis()
                        )
                    )

                    _state.update {
                        it.copy(
                            noteId = -1,
                            title = "",
                            content = "",
                            color = Note.noteColor.first().hashCode(),
                            selectedNote = null
                        )
                    }
                }
            }

            is NoteEvent.ShowHomeScreen -> {
                _state.update {
                    it.copy(
                        noteId = -1,
                        title = "",
                        content = "",
                        color = Note.noteColor.first().hashCode(),
                        selectedNote = null
                    )
                }
            }

//            is NoteEvent.SetSelectedNote -> {
//                _state.update {
//                    it.copy(
//                        selectedNote = event.note
//                    )
//                }
//            }
        }
    }
}