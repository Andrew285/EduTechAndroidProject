package com.example.edutechproject.view.mini_apps.notes

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.findNavController
import com.example.edutechproject.R
import com.example.edutechproject.databinding.ActivityNotesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotesActivity : AppCompatActivity() {
    private val notesViewModel: NotesViewModel by viewModels()
    private lateinit var binding: ActivityNotesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.addNoteBtn.setOnClickListener { view ->
//            view.findNavController().navigate(R.id.)
        }

        notesViewModel.notes.observe(this) { notes ->
            val adapter = NotesAdapter(notes)
            binding.notesRecyclerView.adapter = adapter
        }

        notesViewModel.loadNotes()
    }
}