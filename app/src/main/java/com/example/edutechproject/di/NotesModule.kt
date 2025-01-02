package com.example.edutechproject.di

import android.content.Context
import com.example.edutechproject.data.database.NoteDatabaseHelper
import com.example.edutechproject.data.repository.NoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class NotesModule {

    @Provides
    fun provideNoteDatabaseHelper(@ApplicationContext context: Context): NoteDatabaseHelper = NoteDatabaseHelper(context)

    @Provides
    fun provideNoteRepository(dbHelper: NoteDatabaseHelper): NoteRepository = NoteRepository(dbHelper)
}