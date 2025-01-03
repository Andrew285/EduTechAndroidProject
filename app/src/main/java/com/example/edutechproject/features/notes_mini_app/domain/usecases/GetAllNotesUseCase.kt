package com.example.edutechproject.features.notes_mini_app.domain.usecases

import com.example.edutechproject.features.notes_mini_app.data.models.Note
import com.example.edutechproject.features.notes_mini_app.data.repository.INoteRepository
import com.example.edutechproject.features.notes_mini_app.di.SQLiteNoteRepository
import com.example.edutechproject.features.notes_mini_app.presentation.viewmodels.NotesViewModel
import javax.inject.Inject


class GetAllNotesUseCase @Inject constructor(
    @SQLiteNoteRepository private val sqliteRepository: INoteRepository
) {
    suspend operator fun invoke(loadVariant: NotesViewModel.DATABASE_VARIANT): List<Note> {
        return when (loadVariant) {
            NotesViewModel.DATABASE_VARIANT.SQLITE -> sqliteRepository.getAllNotes()
            else -> sqliteRepository.getAllNotes()
        }
    }
}