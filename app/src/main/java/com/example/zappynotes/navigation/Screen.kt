package com.example.zappynotes.navigation

sealed class Screen(val route : String) {
    data object Home : Screen("home")
    data object AddEditNote : Screen("add_edit_note")

    fun toPassArgs(noteId : Int) : String {
        return "add_edit_note?noteId=$noteId"
    }

}