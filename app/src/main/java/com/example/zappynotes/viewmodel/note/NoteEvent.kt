package com.example.zappynotes.presentation.home.note

import com.example.zappynotes.domain.model.Note

sealed interface NoteEvent {
    data object AddNote: NoteEvent
    data object UpdateNote: NoteEvent
    data class SetTitle(val title: String) : NoteEvent
    data class SetContent(val content: String) : NoteEvent
    data class SetColor(val color: Int) : NoteEvent
    data class SetNoteId(val id: Int) : NoteEvent
   // data class SetSelectedNote(val note: Note) : NoteEvent
    data class DeleteNote(val note: Note) : NoteEvent
    data class GetNoteById(val id: Int) : NoteEvent
    data object ShowHomeScreen : NoteEvent
}