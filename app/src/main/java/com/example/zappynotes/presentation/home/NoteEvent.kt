package com.example.zappynotes.presentation.home

import com.example.zappynotes.domain.model.Note

sealed interface NoteEvent {
    data object SaveNote: NoteEvent
    data class SetTitle(val title: String) : NoteEvent
    data class SetContent(val content: String) : NoteEvent
    data class SetColor(val color: Int) : NoteEvent
    data class DeleteNote(val note: Note) : NoteEvent
    data class QueryNotes(val query: String) : NoteEvent
    data object ToggleSearch: NoteEvent
    data object ShowDialog: NoteEvent

}