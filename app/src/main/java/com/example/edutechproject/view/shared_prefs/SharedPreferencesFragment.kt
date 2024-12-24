package com.example.edutechproject.view.shared_prefs

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.edutechproject.R
import com.example.edutechproject.databinding.FragmentSharedPreferencesBinding

class SharedPreferencesFragment : Fragment() {
    private lateinit var binding: FragmentSharedPreferencesBinding
    val sharedPrefsViewModel: SharedPrefsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSharedPreferencesBinding.inflate(layoutInflater)



        sharedPrefsViewModel.userData.observe(viewLifecycleOwner) { userData ->
            binding.apply {
                nameEditText.setText(userData.name)
                ageEditText.setText(userData.age.toString())
                isStudentCheckBox.isChecked = userData.isStudent
                val colorViewIndex = sharedPrefsViewModel.selectColor(userData.favoriteColor)
                selectColorView(colorViewIndex)
            }
        }

        setClickListeners()

        sharedPrefsViewModel.fetchColors()
        sharedPrefsViewModel.fetchUserData()

        val colors = sharedPrefsViewModel.colors
        val colorAdapter = ColorRecyclerViewAdapter(colors)
        binding.colorContainer.adapter = colorAdapter
        binding.colorContainer.layoutManager = LinearLayoutManager(context)
        binding.colorContainer.visibility = View.GONE

        return binding.root
    }

    private fun selectColorView(colorIndex: Int) {

    }

    private fun saveUserDataUsingSharedPrefs() {
        val sharedPrefs = activity?.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE)
        if (sharedPrefs != null) {
            with (sharedPrefs.edit()) {
                putString("userName", binding.nameEditText.text.toString())
                putInt("userAge", binding.ageEditText.text.toString().toInt())
                putBoolean("isStudent", binding.isStudentCheckBox.isChecked)
                commit()
            }
        }
    }

    private fun clearUserData() {
        with (binding) {
            nameEditText.setText("")
            ageEditText.setText("")
            isStudentCheckBox.isChecked = false
        }
    }

    private fun getDataFromSharedPrefs() {
        val sharedPrefs = activity?.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE)
        if (sharedPrefs != null) {
            with (binding) {
                nameEditText.setText(sharedPrefs.getString("userName", ""))
                ageEditText.setText(sharedPrefs.getInt("userAge", 0).toString())
                isStudentCheckBox.isChecked = sharedPrefs.getBoolean("isStudent", false)
            }
        }
    }

    private fun setClickListeners() {
        with (binding) {
            saveButton.setOnClickListener {
                saveUserDataUsingSharedPrefs()
            }

            loadButton.setOnClickListener {
                getDataFromSharedPrefs()
            }

            clearButton.setOnClickListener {
                clearUserData()
            }
        }
    }
}