package com.example.zappynotes.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zappynotes.data.local.NoteDao
import com.example.zappynotes.domain.model.Note
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NoteViewModel(private val dao: NoteDao) : ViewModel() {

    private val _notes = dao.getNotes()
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
                    dao.deleteNote(event.note)
                }

            }

            is NoteEvent.SaveNote -> {
                val title = _state.value.title
                val content = _state.value.content
                val color = _state.value.color
                if (title.isBlank() || content.isBlank()) {
                    return
                }
                viewModelScope.launch {
                    dao.upsertNote(
                        Note(
                            title = title,
                            content = content,
                            color = color,
                            timestamp = System.currentTimeMillis()
                        )
                    )

                    _state.update {
                        it.copy(
                            title = "",
                            content = "",
                            color = 0,
                        )
                    }
                }
            }



            is NoteEvent.QueryNotes -> {
                viewModelScope.launch {
                    dao.searchNotes(event.query).collect { notes ->
                        _state.update {
                            it.copy(notes = notes)
                        }
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
            is NoteEvent.ToggleSearch -> {
                _state.update {
                    it.copy(
                        toggleSearch = !it.toggleSearch
                    )
                }
        }
            is NoteEvent.ShowDialog -> {
                _state.update {
                    it.copy(
                        isNoteDialogOpen = true
                    )
                }
            }
    }
}
}