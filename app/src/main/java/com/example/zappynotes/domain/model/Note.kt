package com.example.zappynotes.domain.model

import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "note")
data class Note(
    val title: String,
    val content: String,
    val color: Int,
    val timestamp: Long,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0

){
    companion object{
        val noteColor = listOf(Color.Red, Color.Green, Color.Blue, Color.Yellow)
    }
}
