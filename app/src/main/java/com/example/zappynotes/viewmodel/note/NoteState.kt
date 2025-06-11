package com.example.zappynotes.presentation.home.note

import com.example.zappynotes.domain.model.Note

data class NoteState(
    val notes: List<Note> = emptyList(),
    val color: Int = 0,
    val title: String = "",
    val content: String = "",
    val selectedNote : Note?  = null,
    val noteId: Int = -1
)