package com.example.edutechproject.data.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class NoteDatabaseHelper @Inject constructor(@ApplicationContext context: Context):
    SQLiteOpenHelper(context, "notes.db", null, 1) {


    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE notes (" +
                "id INTEGER PRIMARY KEY," +
                "title TEXT," +
                "description TEXT," +
                "date TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS notes")
    }
}