package com.example.edutechproject.features.notes_mini_app.data.repository

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.example.edutechproject.features.notes_mini_app.data.database.NoteDatabaseHelper
import com.example.edutechproject.features.notes_mini_app.data.models.Note
import com.example.edutechproject.utils.Constants.NOTES.NOTE_DATE
import com.example.edutechproject.utils.Constants.NOTES.NOTE_DESCRIPTION
import com.example.edutechproject.utils.Constants.NOTES.NOTE_ID
import com.example.edutechproject.utils.Constants.NOTES.NOTE_TITLE
import com.example.edutechproject.utils.Constants.NOTES.TABLE_NAME
import javax.inject.Inject

class SQLiteNoteRepositoryImp @Inject constructor(private val dbHelper: NoteDatabaseHelper): INoteRepository {
    private val database: SQLiteDatabase by lazy { dbHelper.writableDatabase }

    override suspend fun getAllNotes(): MutableList<Note> {
        val cursor = database.query(TABLE_NAME, null, null, null, null, null, null)
        val notesList = mutableListOf<Note>()
        while (cursor.moveToNext()) {
            val note = Note(
                id = cursor.getInt(cursor.getColumnIndexOrThrow(NOTE_ID)),
                title = cursor.getString(cursor.getColumnIndexOrThrow(NOTE_TITLE)),
                description = cursor.getString(cursor.getColumnIndexOrThrow(NOTE_DESCRIPTION)),
                date = cursor.getLong(cursor.getColumnIndexOrThrow(NOTE_DATE)),
            )
            notesList.add(note)
        }
        cursor.close()
        return notesList
    }

    override suspend fun addNote(note: Note) {
        val contentValues = ContentValues().apply {
            put(NOTE_TITLE, note.title)
            put(NOTE_DESCRIPTION, note.description)
            put(NOTE_DATE, note.date)
        }
        database.insert(TABLE_NAME, null, contentValues)
    }

    override suspend fun editNote(note: Note) {
        val updatedValues = ContentValues().apply {
            put(NOTE_TITLE, note.title)
            put(NOTE_DESCRIPTION, note.description)
            put(NOTE_DATE, note.date)
        }
        val rows = database.update(TABLE_NAME, updatedValues, "id=?", arrayOf(note.id.toString()))
        Log.d("NoteRepository", "Rows updated: $rows")
    }

    override suspend fun deleteNote(noteId: Int) {
        database.delete(TABLE_NAME, "id=?", arrayOf(noteId.toString()))
    }
}