package com.example.edutechproject.view.video_audio_player

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.Player.Listener
import androidx.media3.exoplayer.ExoPlayer
import com.example.edutechproject.R
import com.example.edutechproject.databinding.ActivityVideoAudioPlayerBinding

class VideoAudioPlayerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVideoAudioPlayerBinding
    private lateinit var player: ExoPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityVideoAudioPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val url = intent.extras?.getString(getString(R.string.url))

        val mediaItem = MediaItem.fromUri(url!!)

        player = ExoPlayer.Builder(this).build()
        binding.playerView.player = player

        player.setMediaItem(mediaItem)
        player.prepare()
        player.play()

        player.addListener(
            object : Listener {
                override fun onIsPlayingChanged(isPlaying: Boolean) {
                    super.onIsPlayingChanged(isPlaying)
                    if (isPlaying) {
                        binding.main.setBackgroundColor(getColor(R.color.black))
                    }
                    else {
                        binding.main.setBackgroundColor(getColor(R.color.gray))
                    }
                }
            }
        )
    }

    override fun onResume() {
        super.onResume()

        player.playWhenReady = true
    }

    override fun onStart() {
        super.onStart()

        player.playWhenReady = false
    }

    override fun onDestroy() {
        super.onDestroy()

        player.release()
    }
}