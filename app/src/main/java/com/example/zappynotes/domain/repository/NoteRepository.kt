package com.example.zappynotes.domain.repository

import com.example.zappynotes.data.local.NoteDao
import com.example.zappynotes.domain.model.Note
import kotlinx.coroutines.flow.Flow

class NoteRepository(private val dao : NoteDao) {

    suspend fun addNote(note : Note){
        dao.insertNote(note)
    }

    suspend fun updateNote(note : Note){
        dao.updateNote(note)
    }

    fun getNotes(): Flow<List<Note>> {
        return dao.getNotes()
    }

    suspend fun getNoteById(id : Int) : Note? {
        return dao.getNoteById(id)
    }

    suspend fun deleteNote(note : Note){
        dao.deleteNote(note)
    }

}