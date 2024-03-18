package com.adit.swiftpedia

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AlbumAdapter(private val listAlbum : ArrayList<Album>) : RecyclerView.Adapter<AlbumAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback : OnItemClickCallback
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_album_card, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listAlbum.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, releaseYear, albumDesc, photo) = listAlbum[position]
        holder.albumPhoto.setImageResource(photo)
        holder.albumName.text = name
        holder.yearRelease.text = releaseYear
        holder.albumDesc.text = albumDesc
        holder.itemView.setOnClickListener{ onItemClickCallback.onItemClicked(listAlbum[holder.adapterPosition])}
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Album)
    }
    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val albumPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        val albumName: TextView = itemView.findViewById(R.id.tv_item_name)
        val yearRelease: TextView = itemView.findViewById(R.id.tv_item_release)
        val albumDesc: TextView = itemView.findViewById(R.id.tv_item_description)

    }

}