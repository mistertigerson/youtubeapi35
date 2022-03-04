package com.example.youtubeapi35.ui.playlist.playlists

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.youtubeapi35.model.Items
import com.example.youtubeapi35.R
import com.example.youtubeapi35.databinding.ItemPlaylistBinding
import com.example.youtubeapi35.extension.loadImage

class PlaylistAdapter (private val list: List<Items>, private val clickOnPlaylist: ClickOnPlaylist) :
    RecyclerView.Adapter<PlaylistAdapter.ViewHolder>() {


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
                items.snippet?.thumbnails?.high?.url.toString()
            )
            binding.playlistTitle.text = items.snippet?.title
            binding.playlistCountVideos.text =
                items.contentDetails?.itemCount.toString() + " " + itemView.context.getString(
                    R.string.video_series
                )

            binding.root.setOnClickListener {
                clickOnPlaylist
                    clickOnPlaylist.onClick(items.id, items.contentDetails?.itemCount!!)
            }
        }
    }

    interface ClickOnPlaylist {
        fun onClick(id: String?, position: Int)
    }
}