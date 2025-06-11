package com.example.zappynotes.presentation.home.drawing

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import com.example.zappynotes.domain.model.Drawing

sealed interface DrawingEvent {
    data object OnNewPathStart : DrawingEvent
    data class OnDraw(val offset: Offset) : DrawingEvent
    data object OnPathEnd : DrawingEvent
    data class OnColorChange(val color: Color) : DrawingEvent
    data object OnClearCanvasClick : DrawingEvent
    data object OnSaveClick : DrawingEvent
    data class OnGetDrawingById(val id : Int) : DrawingEvent
    data class OnDeleteClick(val drawing: Drawing) : DrawingEvent
    data object OnShowAddEditScreen : DrawingEvent
}