package com.example.youtubeapi35.ui.playlist.playlist_videos

import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import com.example.youtubeapi35.base.BaseActivity
import com.example.youtubeapi35.databinding.ActivityPlaylistVideosBinding
import com.example.youtubeapi35.ui.playlist.PlaylistViewModel
import com.example.youtubeapi35.ui.playlist.playlists.NAME

class PlaylistVideosActivity : BaseActivity<PlaylistViewModel, ActivityPlaylistVideosBinding>() {

    private lateinit var id: String

    override fun initView() {
        super.initView()
        viewModel = ViewModelProvider(this).get(PlaylistViewModel::class.java)
        id = intent.getStringExtra(NAME)!!
        Log.e(TAG, "video: $id")
    }

    override fun initViewModel() {
        super.initViewModel()
        Log.e("TAG", "video: $id")

        viewModel.getPlayListVideo(id).observe(this) {
            binding.playlistVideos.adapter = PlaylistVideosAdapter(it.items!!)
            binding.playlistTitle.text = it.snippet?.title
            binding.playlistDescription.text = it.snippet?.description
        }
    }

    override fun inflateVB(inflater: LayoutInflater): ActivityPlaylistVideosBinding {
        return ActivityPlaylistVideosBinding.inflate(layoutInflater)
    }

}

