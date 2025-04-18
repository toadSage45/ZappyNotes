package com.example.zappynotes.di

import android.content.Context
import androidx.room.Room
import com.example.zappynotes.data.local.NoteDatabase
import com.example.zappynotes.domain.repository.NoteRepository

object Graph {
    lateinit var database: NoteDatabase

    val noteRepository by lazy {
        NoteRepository(dao = database.noteDao)
    }

    fun provide(context : Context){
        database = Room.databaseBuilder(context, NoteDatabase::class.java, "notes.db").build()
    }
}