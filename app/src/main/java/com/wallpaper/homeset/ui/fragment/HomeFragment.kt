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
import com.wallpaper.homeset.network.model.Status
import com.wallpaper.homeset.ui.adapter.AdapterHome
import com.wallpaper.homeset.util.Constant
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
        viewModel.getCollections(Constant.CLIENT_ID)
        viewModel.getPhotoList(Constant.CLIENT_ID)

        rv_list.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    val visibleItemCount = layoutManager.childCount
                    val totalItemCount = layoutManager.itemCount
                    val pastVisibleItems = layoutManager.findFirstVisibleItemPosition()
                    if (loadMoreItems) {
                        if (visibleItemCount + pastVisibleItems >= totalItemCount) {
                            loadMoreItems = false
                            viewModel.getPhotoList(Constant.CLIENT_ID)
                        }
                    }
                }
            }
        })
    }

    private fun observeChanges() {
        viewModel.getData.observe(requireActivity(), Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.LOADING -> {
                    }
                    Status.SUCCESS -> {
                        progress_bar.visibility = View.GONE
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
                addChip(resources.getString(R.string.all), true)
                for (data in list) {
                    data.title?.let {title ->
                        addChip(title)
                    }
                }
            }
        })

        cg_collection.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId > -1 && group != null) {
                // hit api for extracting collection photos
            }
        }
    }

    private fun addChip(text : String, isChecked : Boolean = false) : Chip {
        val chip = LayoutInflater.from(requireActivity()).inflate(R.layout.layout_chip,null) as Chip
        chip.text = text
        chip.isCheckable = isChecked
        cg_collection.addView(chip)
        if (isChecked) {
            chip.performClick()
        }
        chip.setOnClickListener {
            chip.isCheckable = true
        }
        return chip
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