package com.example.edutechproject.features.notes_mini_app.presentation.ui

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.edutechproject.R
import com.example.edutechproject.features.notes_mini_app.data.models.Note
import com.example.edutechproject.databinding.FragmentEditNoteBinding
import com.example.edutechproject.features.notes_mini_app.presentation.viewmodels.NotesViewModel
import com.example.edutechproject.utils.DateUtils
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar
import java.util.Date

@AndroidEntryPoint
class EditNoteFragment : Fragment() {
    private lateinit var binding: FragmentEditNoteBinding
    private val safeArgs: EditNoteFragmentArgs by navArgs()
    private val notesViewModel: NotesViewModel by viewModels()
    private var databaseVariant: NotesViewModel.DATABASE_VARIANT = NotesViewModel.DATABASE_VARIANT.SQLITE

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentEditNoteBinding.inflate(layoutInflater)

        databaseVariant = safeArgs.databaseVariant
        val note = safeArgs.note
        with (binding) {
            titleEditText.setText(note.title)
            descriptionEditText.setText(note.description)

            notesViewModel.date = Date(note.date)
            val formattedDate = DateUtils.getFormattedDate(notesViewModel.date!!)
            dateTextView.text = formattedDate

            chooseDateBtn.setOnClickListener {
                chooseDateFromDialogPicker()
            }

            editNoteBtn.setOnClickListener {
                val editedNote = Note(
                    id = note.id,
                    title = titleEditText.text.toString(),
                    description = descriptionEditText.text.toString(),
                    date = notesViewModel.date!!.time
                )
                notesViewModel.editNote(editedNote, databaseVariant)
                findNavController().navigate(R.id.action_editNoteFragment_to_notesFragment)
            }
        }

        return binding.root
    }

    private fun chooseDateFromDialogPicker() {
        val initialDate = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog(
            requireContext(), { _, year, month, dayOfMonth ->
                val date = Calendar.getInstance()
                date.set(Calendar.YEAR, year)
                date.set(Calendar.MONTH, month)
                date.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                val formattedDate = DateUtils.getFormattedDate(date.time)
                notesViewModel.date = date.time
                binding.dateTextView.text = formattedDate
            }, initialDate.get(Calendar.YEAR), initialDate.get(Calendar.MONTH), initialDate.get(Calendar.DAY_OF_MONTH)
        )
        // Show the dialog
        datePickerDialog.show()
    }
}