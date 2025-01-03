package com.example.edutechproject.features.notes_mini_app.domain.usecases

import com.example.edutechproject.features.notes_mini_app.data.repository.INoteRepository
import com.example.edutechproject.features.notes_mini_app.di.SQLiteNoteRepository
import com.example.edutechproject.features.notes_mini_app.presentation.viewmodels.NotesViewModel
import javax.inject.Inject

class DeleteNoteUseCase @Inject constructor(
    @SQLiteNoteRepository private val sqlSQLiteNoteRepository: INoteRepository
) {
    suspend operator fun invoke(id: Int, loadVariant: NotesViewModel.DATABASE_VARIANT) {
        when (loadVariant) {
            NotesViewModel.DATABASE_VARIANT.SQLITE -> sqlSQLiteNoteRepository.deleteNote(id)
            else -> sqlSQLiteNoteRepository.deleteNote(id)
        }
    }
}