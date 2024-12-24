package com.example.edutechproject.view.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.edutechproject.R
import com.example.edutechproject.databinding.FragmentHomeBinding
import com.example.edutechproject.data.models.HomeFeatureModel

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(layoutInflater)

        val featuresList = listOf(
            HomeFeatureModel(getString(R.string.feature_name_contact_list), R.id.action_homeFragment_to_contactsFragment),
            HomeFeatureModel(getString(R.string.feature_name_async_tasks), R.id.action_homeFragment_to_asyncTasksFragment),
            HomeFeatureModel(getString(R.string.feature_name_shared_prefs), R.id.action_homeFragment_to_sharedPreferencesFragment),
            HomeFeatureModel(getString(R.string.feature_name_navigation_component), R.id.action_homeFragment_to_navigationComponentFragment),
        )

        loadFeatures(featuresList)

        return binding.root
    }

    private fun loadFeatures(featuresList: List<HomeFeatureModel>) {
        val homeAdapter = HomeRecyclerViewAdapter(featuresList)
        homeAdapter.setOnItemClickListener(object: HomeRecyclerViewAdapter.OnItemClickListener {
            override fun onClick(position: Int, featureModel: HomeFeatureModel) {
                findNavController().navigate(featureModel.actionId)
            }
        })

        binding.featuresRecyclerView.adapter = homeAdapter
        binding.featuresRecyclerView.layoutManager = LinearLayoutManager(requireActivity())
    }
}