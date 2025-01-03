package com.example.edutechproject.features.notes_mini_app.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.edutechproject.databinding.FragmentNotesBinding
import com.example.edutechproject.features.notes_mini_app.presentation.viewmodels.NotesViewModel
import com.example.edutechproject.features.notes_mini_app.presentation.adapters.NotesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotesFragment : Fragment() {
    private val notesViewModel: NotesViewModel by viewModels()
    private lateinit var binding: FragmentNotesBinding
    private var databaseVariant: NotesViewModel.DATABASE_VARIANT = NotesViewModel.DATABASE_VARIANT.SQLITE

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotesBinding.inflate(layoutInflater)

        with (binding) {
            notesRecyclerView.layoutManager = GridLayoutManager(context, GRID_COLUMN_COUNT)
            addNoteBtn.setOnClickListener {
                val action = NotesFragmentDirections.actionNotesFragmentToAddNoteFragment(databaseVariant)
                findNavController().navigate(action)
            }

            sqliteLoadNotesBtn.setOnClickListener {
                databaseVariant = NotesViewModel.DATABASE_VARIANT.SQLITE
                loadNotes(databaseVariant)
                setAddBtnVisibility(View.VISIBLE)
            }

            roomLoadNotesBtn.setOnClickListener {
                databaseVariant = NotesViewModel.DATABASE_VARIANT.ROOM
                loadNotes(databaseVariant)
                setAddBtnVisibility(View.VISIBLE)
            }

            firebaseLoadNotesBtn.setOnClickListener {
                databaseVariant = NotesViewModel.DATABASE_VARIANT.FIREBASE
                loadNotes(databaseVariant)
                setAddBtnVisibility(View.VISIBLE)
            }
        }


        notesViewModel.notes.observe(viewLifecycleOwner) { notes ->
            val adapter = NotesAdapter(notes, object : NotesAdapter.AdapterCallback {
                override fun click(position: Int) {
                    val currentNote = notes[position]
                    val action = NotesFragmentDirections.actionNotesFragmentToNoteFragment(currentNote)
                    findNavController().navigate(action)
                }
            })
            binding.notesRecyclerView.adapter = adapter
        }

        return binding.root
    }

    private fun loadNotes(loadVariant: NotesViewModel.DATABASE_VARIANT) {
        notesViewModel.loadNotes(loadVariant)
    }

    private fun setAddBtnVisibility(visibility: Int) {
        binding.addNoteBtn.visibility = visibility
    }

    companion object {
        private const val GRID_COLUMN_COUNT = 2
    }
}