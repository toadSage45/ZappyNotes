package com.example.zappynotes.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.zappynotes.domain.model.DrawingState
import com.example.zappynotes.domain.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface DrawingStateDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertDrawing(drawingState: DrawingState)

    @Update
    suspend fun updateDrawing(drawingState: DrawingState)

    @Delete
    suspend fun deleteDrawing(drawingState: DrawingState)

    @Query("SELECT * FROM drawing_state ORDER BY timestamp DESC")
    fun getDrawings(): Flow<List<DrawingState>>

    @Query("SELECT * FROM note WHERE id = :id")
    suspend fun getDrawingById(id: Int): DrawingState?
}