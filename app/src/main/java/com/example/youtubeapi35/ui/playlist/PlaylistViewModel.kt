package com.example.youtubeapi35.ui.playlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.youtubeapi35.BuildConfig.API_KEY
import com.example.youtubeapi35.`object`.Constants
import com.example.youtubeapi35.base.BaseViewModel
import com.example.youtubeapi35.model.Playlist
import com.example.youtubeapi35.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlaylistViewModel : BaseViewModel() {

    private val youtubeApi = RetrofitClient.create()

    fun getPlayListVideo(id: String): LiveData<Playlist> {
        return createPlayListVideo(id)
    }

    fun getPlayListVideo(): LiveData<Playlist> {
        return createPlayList()
    }

    private fun createPlayList(): LiveData<Playlist> {

        val data = MutableLiveData<Playlist>()

        youtubeApi.getPlaylists(Constants.PART, Constants.CHANNEL_ID, API_KEY, Constants.MAX_Results)
            .enqueue(object : Callback<Playlist> {
                override fun onResponse(call: Call<Playlist>, response: Response<Playlist>) {
                    if (response.isSuccessful && response.body() != null) {
                        data.value = response.body()
                    }
                }

                override fun onFailure(call: Call<Playlist>, t: Throwable) {
                    print(t.stackTrace)
                }

            })
        return data
    }

    private fun createPlayListVideo(id: String): LiveData<Playlist> {

        val data = MutableLiveData<Playlist>()

        youtubeApi.getPlaylistsVideos(Constants.PART, id, API_KEY, Constants.MAX_Results)
            .enqueue(object : Callback<Playlist> {
                override fun onResponse(call: Call<Playlist>, response: Response<Playlist>) {
                    if (response.isSuccessful && response.body() != null) {
                        data.value = response.body()
                    }
                }

                override fun onFailure(call: Call<Playlist>, t: Throwable) {
                    print(t.stackTrace)
                }

            })
        return data
    }
}