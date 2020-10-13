package com.wallpaper.homeset.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wallpaper.homeset.R
import com.wallpaper.homeset.databinding.ListItemGridBinding
import com.wallpaper.homeset.entity.EntityPhoto
import com.wallpaper.homeset.ui.activity.HomeActivity
import com.wallpaper.homeset.viewmodel.MainViewModel

class AdapterHome(private val context : Context, private val viewModel: MainViewModel) : ListAdapter<EntityPhoto, RecyclerView.ViewHolder>(PhotoItemDiffCallback()) {

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

    fun submitListForCollection(list: List<EntityPhoto>?) {
        super.submitList(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_MAIN) {
            val binding  : ListItemGridBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.list_item_grid, parent, false)
            PhotoViewHolder(context, binding, viewModel)
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

    class PhotoViewHolder(private val context: Context, private val itemBinding: ListItemGridBinding, private val viewModel: MainViewModel) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(
            currentList: MutableList<EntityPhoto>,
            position: Int
        ) {
            val regular = currentList[position].entityUrl?.regular
                ?: throw IllegalArgumentException("thumb url should not be null")
            val color = currentList[position].color
                ?: throw IllegalArgumentException("color should not be null")
            itemBinding.imageUrl = regular
            itemBinding.bgColor = color
            itemBinding.viewModel = viewModel
            itemBinding.position = position
            itemBinding.executePendingBindings()
            
            itemBinding.ivPhoto.setOnClickListener {
                var thumbUrl : String? = currentList[position].entityUrl?.regular
                if (thumbUrl == null) {
                    thumbUrl = currentList[position].coverPhoto?.url?.regular!!
                }
                (context as HomeActivity).openFullScreenFragment(thumbUrl, position, itemBinding.ivPhoto)
            }
        }
    }
}