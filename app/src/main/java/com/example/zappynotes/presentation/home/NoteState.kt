package com.example.zappynotes.presentation.home

import com.example.zappynotes.domain.model.Note

data class NoteState(
    val notes: List<Note> = emptyList(),
    val color: Int = 0,
    val title: String = "",
    val content: String = "",
    val toggleSearch: Boolean = false,
    val isNoteDialogOpen: Boolean = false
)