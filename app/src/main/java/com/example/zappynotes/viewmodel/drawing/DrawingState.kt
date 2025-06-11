package com.example.zappynotes.presentation.home.drawing

import androidx.compose.ui.graphics.Color
import com.example.zappynotes.domain.model.Drawing
import com.example.zappynotes.domain.model.PathData

data class DrawingState(
    val selectedColor: Color = Color.Black,
    val currentPath : PathData? = null,
    val paths : List<PathData> = emptyList(),
    val selectedDrawing : Drawing? = null
)
