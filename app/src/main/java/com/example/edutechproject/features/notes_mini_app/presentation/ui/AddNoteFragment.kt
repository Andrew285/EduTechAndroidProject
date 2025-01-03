package com.example.edutechproject.features.notes_mini_app.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.edutechproject.features.notes_mini_app.data.models.Note
import com.example.edutechproject.databinding.FragmentAddNoteBinding
import com.example.edutechproject.features.notes_mini_app.presentation.viewmodels.NotesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddNoteFragment : Fragment() {
    private val notesViewModel: NotesViewModel by viewModels()
    private lateinit var binding: FragmentAddNoteBinding
    private val safeArgs: AddNoteFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAddNoteBinding.inflate(layoutInflater)

        if (areInputFieldsValid()) {
            with (binding) {
                addNoteBtn.setOnClickListener {
                    val note = Note(
                        title = titleEditText.text.toString(),
                        description = descriptionEditText.text.toString(),
                    )

                    val databaseVariant = safeArgs.databaseVariant
                    notesViewModel.addNote(note, databaseVariant)
                    findNavController().popBackStack()
                }
            }
        }

        return binding.root
    }

    private fun areInputFieldsValid(): Boolean {
        with (binding) {
            return titleEditText.text.isNullOrEmpty() && descriptionEditText.text.isNullOrEmpty()
        }
    }
}