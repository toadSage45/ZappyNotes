package com.example.zappynotes.domain.model

import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "drawing_state")
data class DrawingState(
    @PrimaryKey
    val id: Int = 0,
    val selectedColor: Color = Color.Black,
    val currentPath : PathData? = null,
    val paths : List<PathData> = emptyList(),
    val timestamp: Long,
)

val allColors = listOf(
    Color.Black,
    Color.Red,
    Color.Blue,
    Color.Green,
    Color.Yellow,
    Color.Magenta,
    Color.Cyan,
)
