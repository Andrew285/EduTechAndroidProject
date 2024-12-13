package com.example.edutechproject.features.shared_prefs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.edutechproject.R
import com.example.edutechproject.databinding.FragmentSharedPreferencesBinding

class SharedPreferencesFragment : Fragment() {
    private lateinit var binding: FragmentSharedPreferencesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSharedPreferencesBinding.inflate(layoutInflater)
        return binding.root
    }
}