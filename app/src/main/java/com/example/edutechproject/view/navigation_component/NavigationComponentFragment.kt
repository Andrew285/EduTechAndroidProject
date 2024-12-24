package com.example.edutechproject.view.navigation_component

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.edutechproject.R
import com.example.edutechproject.databinding.FragmentNavigationComponentBinding

class NavigationComponentFragment : Fragment() {
    private lateinit var binding: FragmentNavigationComponentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentNavigationComponentBinding.inflate(layoutInflater)

        with (binding) {
            passDataBtn.setOnClickListener {
                val action = NavigationComponentFragmentDirections.actionNavigationComponentFragmentToPassedSafeArgsFragment(
                    Number = 123456789,
                    Text = "Android",
                    BoolValue = true
                )
                findNavController().navigate(action)
            }

            passNoDataBtn.setOnClickListener {
                findNavController().navigate(R.id.action_navigationComponentFragment_to_passedSafeArgsFragment)
            }
        }


        return binding.root
    }
}