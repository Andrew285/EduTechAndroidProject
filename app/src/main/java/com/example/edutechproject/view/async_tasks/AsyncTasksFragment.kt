package com.example.edutechproject.view.async_tasks

import DownloadImagesTask
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.edutechproject.databinding.FragmentAsyncTasksBinding

class AsyncTasksFragment : Fragment() {
    private lateinit var binding: FragmentAsyncTasksBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAsyncTasksBinding.inflate(layoutInflater)

        val imageUrl = "https://picsum.photos/200/300"
        val imageView = binding.imageView
        val progressBar = binding.progressBar

        val task = DownloadImagesTask(requireContext(), imageView, progressBar)
        task.execute(imageUrl)

        return binding.root
    }
}