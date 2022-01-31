package com.example.youtubeapi35.ui.playlist

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.youtubeapi35.BuildConfig.BASE_URl
import com.example.youtubeapi35.base.BaseActivity
import com.example.youtubeapi35.databinding.ActivityPlaylistBinding

class PlaylistActivity : BaseActivity<PlaylistViewModel, ActivityPlaylistBinding>() {

    override fun initView() {
        super.initView()
        viewModel = ViewModelProvider(this).get(PlaylistViewModel::class.java)
    }

    override fun initViewModel() {
        super.initViewModel()
        viewModel.getPlayList().observe(this){
            Log.e(TAG, "initViewModel: "+ it.kind.toString() )
            Toast.makeText(this, it.kind.toString(), Toast.LENGTH_SHORT).show()
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