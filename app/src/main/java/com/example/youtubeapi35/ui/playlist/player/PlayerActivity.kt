package com.example.youtubeapi35.ui.playlist.player

import android.os.Bundle
import android.widget.Toast
import com.example.youtubeapi35.BuildConfig.API_KEY
import com.example.youtubeapi35.R
import com.example.youtubeapi35.ui.playlist.playlist_videos.PlaylistVideosActivity.Companion.VIDEO_ID
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView

class PlayerActivity : YouTubeBaseActivity() {

    //    val videoId: String? = intent.getStringExtra(VIDEO_ID)
    val videoId: String? = "0mnEsPMg6H4"

    private lateinit var youtubePlayer: YouTubePlayerView
    lateinit var youtubePlayerInit: YouTubePlayer.OnInitializedListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)

        initView()
    }

    private fun initView() {

        youtubePlayer = findViewById(R.id.youtubeplayer)
        youtubePlayerInit = object : YouTubePlayer.OnInitializedListener {
            override fun onInitializationSuccess(
                p0: YouTubePlayer.Provider?,
                p1: YouTubePlayer?,
                p2: Boolean
            ) {
                p1?.loadVideo(VIDEO_ID)

            }

            override fun onInitializationFailure(
                p0: YouTubePlayer.Provider?,
                p1: YouTubeInitializationResult?
            ) {

                Toast.makeText(applicationContext, "Failed ", Toast.LENGTH_SHORT).show()
            }


        }
        youtubePlayer.initialize(API_KEY, youtubePlayerInit)

    }
}