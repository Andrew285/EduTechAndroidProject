package com.example.edutechproject.features.notes_mini_app.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.edutechproject.databinding.FragmentNoteBinding
import com.example.edutechproject.features.notes_mini_app.presentation.viewmodels.NotesViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.DateFormat

@AndroidEntryPoint
class NoteFragment : Fragment() {
    private lateinit var binding: FragmentNoteBinding
    private val passedSafeArgs: NoteFragmentArgs by navArgs()
    private val notesViewModel: NotesViewModel by viewModels()
    private var databaseVariant: NotesViewModel.DATABASE_VARIANT = NotesViewModel.DATABASE_VARIANT.SQLITE

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentNoteBinding.inflate(layoutInflater)

        with (binding) {
            val note = passedSafeArgs.note
            titleTextView.text = note.title
            descriptionTextView.text = note.description
            dateTextView.text = DateFormat.getInstance().format(note.date)

            databaseVariant = passedSafeArgs.databaseVariant

            editNoteBtn.setOnClickListener {
                val action = NoteFragmentDirections.actionNoteFragmentToEditNoteFragment(note, databaseVariant)
                findNavController().navigate(action)
            }

            deleteNoteBtn.setOnClickListener {
                notesViewModel.editNote(note, databaseVariant)
            }
        }

        // Inflate the layout for this fragment
        return binding.root
    }

}