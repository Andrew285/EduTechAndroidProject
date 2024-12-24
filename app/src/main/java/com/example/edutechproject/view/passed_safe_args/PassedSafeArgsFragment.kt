package com.example.edutechproject.view.passed_safe_args

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.edutechproject.R
import com.example.edutechproject.databinding.FragmentPassedSafeArgsBinding

class PassedSafeArgsFragment : Fragment() {
    private lateinit var binding: FragmentPassedSafeArgsBinding
    private val passedSafeArgs: PassedSafeArgsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPassedSafeArgsBinding.inflate(layoutInflater)

        val number = passedSafeArgs.Number
        val text = passedSafeArgs.Text
        val bool = passedSafeArgs.BoolValue

        with (binding) {
            passedDataStatus.text = if (bool) getString(R.string.passedDataSuccess) else getString(R.string.passedDataError)
            passedNumberTextView.text = getString(R.string.numberDefaultValue, number)
            passedStringTextView.text = getString(R.string.stringDefaultValue, text)
            passedBoolCheckBox.isChecked = bool
        }

        return binding.root
    }
}