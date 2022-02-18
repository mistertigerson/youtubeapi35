package com.example.youtubeapi35.ui.playlist.playlists

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import com.example.youtubeapi35.BuildConfig.BASE_URl
import com.example.youtubeapi35.base.BaseActivity
import com.example.youtubeapi35.databinding.ActivityPlaylistBinding
import com.example.youtubeapi35.ui.playlist.PlaylistViewModel
import com.example.youtubeapi35.ui.playlist.playlist_videos.PlaylistVideosActivity

class PlaylistActivity : BaseActivity<PlaylistViewModel, ActivityPlaylistBinding>() {

    override fun initView() {
        super.initView()
        viewModel = ViewModelProvider(this).get(PlaylistViewModel::class.java)
    }

    override fun initViewModel() {
        super.initViewModel()
        viewModel.getPlayListVideo().observe(this) {
            Log.e(TAG, "initViewModel: " + it.kind.toString())
            binding.recycler.adapter =
                PlaylistAdapter(it.items!!, object : PlaylistAdapter.ClickOnPlaylist {
                    override fun onClick(id: String?, position: Int) {
                        Intent(this@PlaylistActivity, PlaylistVideosActivity::class.java).apply {
                            putExtra(NAME, id)
                            Log.e("TAG", "playlist: $id")
                            startActivity(this)
                        }
                    }
                })

        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BASE_URl

    }

    override fun inflateVB(inflater: LayoutInflater): ActivityPlaylistBinding {
        return ActivityPlaylistBinding.inflate(layoutInflater)
    }

}

const val
        NAME: String = "key"
