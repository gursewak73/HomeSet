package com.wallpaper.homeset.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wallpaper.homeset.R
import com.wallpaper.homeset.entity.EntityPhoto
import kotlinx.android.synthetic.main.adapter_photo.view.*

class AdapterHome : ListAdapter<EntityPhoto, AdapterHome.PhotoViewHolder>(PhotoItemDiffCallback()) {

    class PhotoItemDiffCallback : DiffUtil.ItemCallback<EntityPhoto>() {
        override fun areItemsTheSame(oldItem: EntityPhoto, newItem: EntityPhoto): Boolean = oldItem == newItem

        override fun areContentsTheSame(oldItem: EntityPhoto, newItem: EntityPhoto): Boolean = oldItem == newItem

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return PhotoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.adapter_photo, parent, false))
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bindTo(photo = getItem(position))
    }

    class PhotoViewHolder(var view : View) : RecyclerView.ViewHolder(view) {

        fun bindTo(photo : EntityPhoto) {
            val regular = photo.entityUrl?.regular
                ?: throw IllegalArgumentException("thumb url should not be null")
//            val color = photo.color ?: throw IllegalArgumentException("color should not be null")
//            holder.thumb.setBackgroundColor(Color.parseColor(color))
            Glide.with(view.context)
                .load(regular)
                .into(view.iv_photo)
//            Glide.with(view.context).load().into(imageView);
        }

    }
}