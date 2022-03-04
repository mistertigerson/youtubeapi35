package com.example.youtubeapi35.ui.playlist.playlist_videos

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.youtubeapi35.R
import com.example.youtubeapi35.databinding.ItemPlaylistBinding
import com.example.youtubeapi35.extension.loadImage
import com.example.youtubeapi35.model.Items
import com.example.youtubeapi35.model.ResourceId
import com.example.youtubeapi35.ui.playlist.player.PlayerActivity
import com.example.youtubeapi35.ui.playlist.playlist_videos.PlaylistVideosActivity.Companion.VIDEO_ID

class PlaylistVideosAdapter (private val list: List<Items>, private val clickOnPlaylist2: ClickOnPlaylist2) :
    RecyclerView.Adapter<PlaylistVideosAdapter.ViewHolder>() {

    private lateinit var binding: ItemPlaylistBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemPlaylistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(itemView: ItemPlaylistBinding) : RecyclerView.ViewHolder(itemView.root) {

        @SuppressLint("SetTextI18n")
        fun onBind(items: Items) {
            var id : String
            binding.playlist.isVisible = false
            binding.backTrans.isVisible = false
            binding.playlistCountVideos.isVisible = false
            binding.playlistPhoto.loadImage(
                items.snippet?.thumbnails?.high?.url!!
            )
            binding.playlistTitle.text = items.snippet?.title
            binding.playlistCountVideos.text =
                items.contentDetails?.itemCount.toString() + " " + itemView.context.getString(
                    R.string.video_series
                )
            binding.root.setOnClickListener {
                clickOnPlaylist2.onClick(items.snippet!!.resourceId.videoId)
                VIDEO_ID = items.snippet!!.resourceId.videoId
                }
        }

    }

    interface ClickOnPlaylist2 {
        fun onClick(id: String)
    }
}