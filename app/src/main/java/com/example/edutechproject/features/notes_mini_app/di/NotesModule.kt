package com.example.edutechproject.features.notes_mini_app.di

import android.content.Context
import com.example.edutechproject.features.notes_mini_app.data.database.NoteDatabaseHelper
import com.example.edutechproject.features.notes_mini_app.data.repository.INoteRepository
import com.example.edutechproject.features.notes_mini_app.data.repository.SQLiteNoteRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class SQLiteNoteRepository

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RoomNoteRepository

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class FirebaseNoteRepository

@Module
@InstallIn(SingletonComponent::class)
class NotesModule {

    @Provides
    fun provideNoteDatabaseHelper(@ApplicationContext context: Context): NoteDatabaseHelper = NoteDatabaseHelper(context)

    @SQLiteNoteRepository
    @Provides
    fun provideSqlNoteRepository(dbHelper: NoteDatabaseHelper): INoteRepository = SQLiteNoteRepositoryImp(dbHelper)
}