package com.wallpaper.homeset.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.wallpaper.homeset.R
import com.wallpaper.homeset.entity.EntityPhoto
import com.wallpaper.homeset.network.model.Status
import com.wallpaper.homeset.ui.adapter.AdapterHome
import com.wallpaper.homeset.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.home_fragment.*


class HomeFragment : Fragment() {

    private val viewModel by activityViewModels<MainViewModel>()
    private lateinit var adapter: AdapterHome
    private var loadMoreItems = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.title = resources.getString(R.string.app_name)
        adapter = AdapterHome()
        val layoutManager = getLayoutManager()
        rv_list.layoutManager = layoutManager
        rv_list.adapter = adapter
        observeChanges()

        rv_list.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (viewModel.listType != MainViewModel.LIST_TYPE_ALL) {
                    loadMoreItems = true
                    return
                }

                if (dy > 0) {
                    val visibleItemCount = layoutManager.childCount
                    val totalItemCount = layoutManager.itemCount
                    val pastVisibleItems = layoutManager.findFirstVisibleItemPosition()
                    if (loadMoreItems) {
                        if (visibleItemCount + pastVisibleItems >= totalItemCount) {
                            loadMoreItems = false
                            viewModel.getPhotoList()
                        }
                    }
                }
            }
        })
    }

    private fun observeChanges() {
        viewModel.getData.observe(requireActivity(), Observer {
            if (viewModel.listType != MainViewModel.LIST_TYPE_ALL) {
                return@Observer
            }
            it?.let { resource ->
                when (resource.status) {
                    Status.LOADING -> {
                    }
                    Status.SUCCESS -> {
                        progress_bar.visibility = View.GONE
                        rv_list.visibility = View.VISIBLE
                        adapter.submitList(resource.data!!)
                        loadMoreItems = true
                    }
                    Status.ERROR -> {
                        progress_bar.visibility = View.GONE
                    }
                }
            }
        })

        viewModel.getCollectionData.observe(requireActivity(), Observer {
            it.data?.let { list ->
                list.forEachIndexed { index, entityPhoto ->
                    entityPhoto.title?.let { title ->
                        addChip(entityPhoto, index, index == 0)
                    }
                }

                cg_collection.setOnCheckedChangeListener { group, checkedId ->
                    if (checkedId > -1 && group != null && checkedId <= list.size - 1) {
                        val chip: Chip = group.findViewById(checkedId)
                        if (chip.text == resources.getString(R.string.all)) {
                            // extract photos
                            viewModel.getPhotoList()
                            return@setOnCheckedChangeListener
                        }
                        // hit api for extracting collection photos
                        list[checkedId].id?.let { id ->
                            viewModel.getCollectionPhotos(id)
                        }
                    } else {
                        val chip: Chip = group.findViewById(0)
                        chip.performClick()
                        viewModel.getPhotoList()
                    }
                }
            }
        })

        viewModel.getCollectionPhotos.observe(requireActivity(), Observer {
            it.data?.let { list ->
                adapter.submitListForCollection(list)
            }
        })
    }

    private fun addChip(photo: EntityPhoto, index: Int, isSelected: Boolean = false) {
        val chip = LayoutInflater.from(requireActivity()).inflate(R.layout.layout_chip, null) as Chip
        chip.apply {
            text = photo.title
            id = index
            tag = index
            isChecked = isSelected
            cg_collection.addView(this)
        }
    }


    private fun getLayoutManager(): LinearLayoutManager {
//        val layoutManager = GridLayoutManager(context, 2)
        val layoutManager = LinearLayoutManager(context)
//        layoutManager.spanSizeLookup = object : SpanSizeLookup() {
//            override fun getSpanSize(position: Int): Int {
//                 last position
//                return if (position == adapter.itemCount - 1) 2 else 1
//            }
//        }
        return layoutManager
    }
}