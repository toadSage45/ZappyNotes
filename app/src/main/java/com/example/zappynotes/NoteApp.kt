package com.example.zappynotes

import android.app.Application
import com.example.zappynotes.di.Graph

class NoteApp : Application(){
    override fun onCreate() {
        super.onCreate()
        Graph.provide(this)
    }
}