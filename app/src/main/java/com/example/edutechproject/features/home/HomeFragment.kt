package com.example.edutechproject.features.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.edutechproject.R
import com.example.edutechproject.adapters.HomeRecyclerViewAdapter
import com.example.edutechproject.databinding.FragmentHomeBinding
import com.example.edutechproject.features.async_tasks.AsyncTasksFragment
import com.example.edutechproject.features.contacts.ContactsFragment
import com.example.edutechproject.features.shared_prefs.SharedPreferencesFragment

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(layoutInflater)

        val featuresList = listOf(
            HomeFeatureModel(getString(R.string.feature_name_contact_list), ContactsFragment::class.java),
            HomeFeatureModel(getString(R.string.feature_name_async_tasks), AsyncTasksFragment::class.java),
            HomeFeatureModel(getString(R.string.feature_name_shared_prefs), SharedPreferencesFragment::class.java)
        )

        loadFeatures(featuresList)

        return binding.root
    }

    private fun loadFeatures(featuresList: List<HomeFeatureModel>) {
        val homeAdapter = HomeRecyclerViewAdapter(featuresList)
        homeAdapter.setOnItemClickListener(object: HomeRecyclerViewAdapter.OnItemClickListener {
            override fun onClick(position: Int, featureModel: HomeFeatureModel) {
                val nextFragment = featureModel.fragmentClass
                parentFragmentManager
                    .beginTransaction()
                    .replace(R.id.mainFrameLayout, nextFragment.newInstance())
                    .addToBackStack(null)
                    .commit()
            }
        })

        binding.featuresRecyclerView.adapter = homeAdapter
        binding.featuresRecyclerView.layoutManager = LinearLayoutManager(requireActivity())
    }
}