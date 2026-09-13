package com.example.livetv

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.livetv.databinding.ActivityMainBinding
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer

data class Channel(
    val name: String,
    val url: String
)

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var player: ExoPlayer

    private val channels = listOf(
        Channel(
            "Demo Channel 1",
            "http://m.live.net.sa:1935/live/quran/playlist.m3u8"
        ),
        Channel(
            "Demo Channel 2",
            "http://m.live.net.sa:1935/live/quran/playlist.m3u8"
        ),
        Channel(
            "Demo Channel 3",
            "https://media2.streambrothers.com:1936/8122/8122/playlist.m3u8"
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        player = ExoPlayer.Builder(this).build()
        binding.playerView.player = player

        val adapter = ChannelAdapter(channels) { channel ->
            playChannel(channel)
        }

        binding.channelList.layoutManager =
            LinearLayoutManager(this)

        binding.channelList.adapter = adapter

        if (channels.isNotEmpty()) {
            playChannel(channels[0])
        }
    }

    private fun playChannel(channel: Channel) {

        binding.channelTitle.text = channel.name

        val mediaItem = MediaItem.fromUri(channel.url)

        player.setMediaItem(mediaItem)
        player.prepare()
        player.play()
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }
}
