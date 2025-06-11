package com.example.zappynotes.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.zappynotes.domain.model.Drawing
import com.example.zappynotes.domain.model.Note


@Database(
    entities = [Note::class , Drawing::class],
    version = 1,
    exportSchema = false
)


abstract class NoteDatabase : RoomDatabase() {
    abstract val noteDao : NoteDao
}