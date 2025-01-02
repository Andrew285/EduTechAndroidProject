package com.example.edutechproject.view.mini_apps.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.edutechproject.data.models.Note
import com.example.edutechproject.data.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val noteRepository: NoteRepository
): ViewModel() {
    private val _notes = MutableLiveData<List<Note>>()
    val notes: LiveData<List<Note>> get() = _notes

    fun loadNotes() {
        viewModelScope.launch {
            _notes.value = noteRepository.getAllNotes()
        }
    }

    fun addNote(note: Note) {
        viewModelScope.launch {
            noteRepository.addNote(note)
            loadNotes()
        }
    }

    fun deleteNote(id: Int) {
        viewModelScope.launch {
            noteRepository.deleteNoteById(id)
            loadNotes()
        }
    }

    fun editNote(note: Note) {
        viewModelScope.launch {
            noteRepository.editNote(note)
            loadNotes()
        }
    }
}