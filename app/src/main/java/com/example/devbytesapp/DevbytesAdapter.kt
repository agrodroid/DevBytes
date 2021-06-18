package com.example.devbytesapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.devbytesapp.models.DatabaseVideo


class DevbytesAdapter(val clickListener: DevBytesClick): RecyclerView.Adapter<DevbytesAdapter.DevbytesViewHolder>(){

    var data  = listOf<DatabaseVideo>()
    set(value){
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DevbytesViewHolder {
        return DevbytesViewHolder.from(parent)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: DevbytesViewHolder, position: Int) {
        val currentItem = data[position]
        holder.bind(currentItem, clickListener)
    }

    class DevbytesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        fun bind(
                currentItem: DatabaseVideo,
                clickListener: DevBytesClick
        ) {
            titleTextView.text = currentItem.title
            updatedTextView.text = currentItem.updatedDate
            descriptionTextView.text = currentItem.shortDescription
            thumbnailImageView.load(currentItem.thumbnail)
            videoButton.setOnClickListener {
                clickListener.onClick(currentItem)
            }
        }

        companion object {
            fun from(parent: ViewGroup): DevbytesViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val itemView = layoutInflater.inflate(R.layout.devbytes_list_item, parent, false)
                return DevbytesViewHolder(itemView)
            }
        }

        val thumbnailImageView: ImageView = itemView.findViewById(R.id.thumbnail_image_view)
        val titleTextView: TextView = itemView.findViewById(R.id.title_text_view)
        val updatedTextView: TextView = itemView.findViewById(R.id.updated_text_view)
        val descriptionTextView: TextView = itemView.findViewById(R.id.description_text_view)
        val videoButton: TextView = itemView.findViewById(R.id.button_video)

    }

    class DevBytesClick(val clickListener: (databaseVideo: DatabaseVideo) -> Unit){
        fun onClick(databaseVideo: DatabaseVideo) = clickListener(databaseVideo)
    }
}