package com.example.edutechproject.features.notes_mini_app.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.edutechproject.features.notes_mini_app.data.models.Note
import com.example.edutechproject.features.notes_mini_app.domain.usecases.AddNoteUseCase
import com.example.edutechproject.features.notes_mini_app.domain.usecases.DeleteNoteUseCase
import com.example.edutechproject.features.notes_mini_app.domain.usecases.EditNoteUseCase
import com.example.edutechproject.features.notes_mini_app.domain.usecases.GetAllNotesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val getAllNotesUseCase: GetAllNotesUseCase,
    private val addNoteUseCase: AddNoteUseCase,
    private val editNoteUseCase: EditNoteUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase
): ViewModel() {
    private val _notes = MutableLiveData<List<Note>>()
    val notes: LiveData<List<Note>> get() = _notes
    var date: Date? = null

    fun loadNotes(loadVariant: DATABASE_VARIANT) {
        viewModelScope.launch {
            _notes.value = getAllNotesUseCase(loadVariant)
        }
    }

    fun addNote(note: Note, loadVariant: DATABASE_VARIANT) {
        viewModelScope.launch {
            addNoteUseCase(note, loadVariant)
            loadNotes(loadVariant)
        }
    }

    fun deleteNote(id: Int, loadVariant: DATABASE_VARIANT) {
        viewModelScope.launch {
            deleteNoteUseCase(id, loadVariant)
            loadNotes(loadVariant)
        }
    }

    fun editNote(note: Note, loadVariant: DATABASE_VARIANT) {
        viewModelScope.launch {
            editNoteUseCase(note, loadVariant)
            loadNotes(loadVariant)
        }
    }

//    fun getById(id: Int) {
//        viewModelScope.launch {
//            loadNotes()
//        }
//    }

    enum class DATABASE_VARIANT {
        SQLITE, ROOM, FIREBASE
    }
}