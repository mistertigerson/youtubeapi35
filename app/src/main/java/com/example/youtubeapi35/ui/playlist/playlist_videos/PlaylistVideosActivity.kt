package com.example.youtubeapi35.ui.playlist.playlist_videos

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore.Video.Thumbnails.VIDEO_ID
import android.util.Log
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import com.example.youtubeapi35.ui.playlist.player.PlayerActivity
import com.example.youtubeapi35.`object`.Constants
import com.example.youtubeapi35.base.BaseActivity
import com.example.youtubeapi35.databinding.ActivityPlaylistVideosBinding
import com.example.youtubeapi35.ui.playlist.PlaylistViewModel
import com.example.youtubeapi35.ui.playlist.playlists.PlaylistAdapter

class PlaylistVideosActivity : BaseActivity<PlaylistViewModel, ActivityPlaylistVideosBinding>() {

    private lateinit var id: String
    private var count: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onClick()
    }

    private fun onClick(){
        binding.back.setOnClickListener{
            onBackPressed()
        }
    }

    override fun initView() {
        super.initView()
        viewModel = ViewModelProvider(this).get(PlaylistViewModel::class.java)
        id = intent.getStringExtra(Constants.NAME)!!
        count = intent.getIntExtra(Constants.COUNT, 0)
        Log.e(TAG, "count: ${count}")
        Log.e(TAG, "video: $id")
    }

    override fun initViewModel() {
        super.initViewModel()
        viewModel.getPlayListVideo(id).observe(this) {
            binding.playlistVideos.adapter =
                PlaylistVideosAdapter(it.items!!, object : PlaylistVideosAdapter.ClickOnPlaylist2 {
                    override fun onClick(id: String) {
                        Intent(this@PlaylistVideosActivity, PlayerActivity::class.java).apply {
//                            Log.e(TAG, "onClick: ${it.resourceId.videoId}", )
                            startActivity(this)
                        }
                    }
                })
            Log.e(TAG, "title:${it.snippet?.title.toString()} ")
            Log.e(TAG, "title:${it.snippet?.description.toString()} ")
            binding.count.text = "$count video series"

        }

    }

    override fun inflateVB(inflater: LayoutInflater): ActivityPlaylistVideosBinding {
        return ActivityPlaylistVideosBinding.inflate(layoutInflater)
    }


    companion object{
        var VIDEO_ID = "video"
    }
}

