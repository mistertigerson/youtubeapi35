package com.example.youtubeapi35.ui.playlist.playlist_videos

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.youtubeapi35.R
import com.example.youtubeapi35.databinding.ItemPlaylistBinding
import com.example.youtubeapi35.extension.loadImage
import com.example.youtubeapi35.model.Items

class PlaylistVideosAdapter (private val list: List<Items>) :
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
            binding.playlistPhoto.loadImage(
                items.snippet?.thumbnails?.default?.url!!
            )
            binding.playlistTitle.text = items.snippet?.title
            binding.playlistCountVideos.text =
                items.contentDetails?.itemCount.toString() + " " + itemView.context.getString(
                    R.string.video_series
                )
        }

    }
}