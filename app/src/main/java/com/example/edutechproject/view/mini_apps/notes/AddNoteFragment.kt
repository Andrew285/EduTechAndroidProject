package com.example.edutechproject.view.mini_apps.notes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.edutechproject.R
import com.example.edutechproject.databinding.FragmentAddNoteBinding

class AddNoteFragment : Fragment() {
    private lateinit var binding: FragmentAddNoteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAddNoteBinding.inflate(layoutInflater)

        with (binding) {

        }

        return binding.root
    }
}