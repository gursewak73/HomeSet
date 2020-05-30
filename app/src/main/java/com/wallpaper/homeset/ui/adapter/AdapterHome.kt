package com.wallpaper.homeset.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wallpaper.homeset.R
import com.wallpaper.homeset.entity.EntityPhoto
import com.wallpaper.homeset.ui.fragment.HomeFragmentDirections
import kotlinx.android.synthetic.main.list_item_grid.view.*

class AdapterHome : ListAdapter<EntityPhoto, RecyclerView.ViewHolder>(PhotoItemDiffCallback()) {

    companion object {
        private const val VIEW_MAIN = 0
        private const val VIEW_PROGRESS = 1
    }

    class PhotoItemDiffCallback : DiffUtil.ItemCallback<EntityPhoto>() {

        override fun areItemsTheSame(oldItem: EntityPhoto, newItem: EntityPhoto): Boolean {
            return oldItem.id == newItem.id
        }


        override fun areContentsTheSame(oldItem: EntityPhoto, newItem: EntityPhoto): Boolean {
            return oldItem == newItem
        }
    }

    override fun submitList(list: List<EntityPhoto>?) {
        super.submitList(list?.let { ArrayList(it) })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_MAIN) {
            PhotoViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.list_item_grid, parent, false)
            )
        } else {
            ProgressViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.list_item_progress, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is PhotoViewHolder)
            holder.bind(currentList, position)
    }

    override fun getItemViewType(position: Int): Int {
        if (position == itemCount - 1) {
            return VIEW_PROGRESS
        }
        return VIEW_MAIN
    }

    class ProgressViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    class PhotoViewHolder(var view: View) : RecyclerView.ViewHolder(view) {

        fun bind(
            currentList: MutableList<EntityPhoto>,
            position: Int
        ) {
            val regular = currentList[position].entityUrl?.regular
                ?: throw IllegalArgumentException("thumb url should not be null")
            val color = currentList[position].color ?: throw IllegalArgumentException("color should not be null")
            view.iv_photo.setBackgroundColor(Color.parseColor(color))
            Glide.with(view.context)
                .load(regular)
                .into(view.iv_photo)
            view.iv_photo.setOnClickListener { view ->
                view.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToFullScreenFragment(
                    currentList.toTypedArray()))
            }
        }
    }
}