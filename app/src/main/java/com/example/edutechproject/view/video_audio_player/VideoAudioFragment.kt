package com.example.edutechproject.view.video_audio_player

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.edutechproject.R
import com.example.edutechproject.databinding.FragmentVideoAudioBinding


class VideoAudioFragment : Fragment() {
    private lateinit var binding: FragmentVideoAudioBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVideoAudioBinding.inflate(layoutInflater)

        with (binding) {
            firstVideoBtn.setOnClickListener {
                val url = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/VolkswagenGTIReview.mp4"
                goToPlayer(url)
            }

            secondVideoBtn.setOnClickListener {
                val url = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/TearsOfSteel.mp4"
                goToPlayer(url)
            }

            audioBtn.setOnClickListener {
                val url = "https://audio-edge-5bkfj.fra.h.radiomast.io/ref-128k-mp3-stereo"
                goToPlayer(url)
            }
        }

        return binding.root
    }

    private fun goToPlayer(url: String) {
        val intent = Intent(context, VideoAudioPlayerActivity::class.java)
        intent.putExtra(getString(R.string.url), url)
        startActivity(intent)
    }
}