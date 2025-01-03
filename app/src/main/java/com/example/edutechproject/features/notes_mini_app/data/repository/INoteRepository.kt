package com.example.edutechproject.features.notes_mini_app.data.repository

import com.example.edutechproject.features.notes_mini_app.data.models.Note

interface INoteRepository {
    suspend fun getAllNotes(): List<Note>
    suspend fun addNote(note: Note)
    suspend fun editNote(note: Note)
    suspend fun deleteNote(id: Int)
}