package com.wallpaper.homeset.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import androidx.recyclerview.widget.LinearLayoutManager
import com.wallpaper.homeset.R
import com.wallpaper.homeset.listener.PaginationListener
import com.wallpaper.homeset.network.model.Status
import com.wallpaper.homeset.ui.adapter.AdapterHome
import com.wallpaper.homeset.util.Constant
import com.wallpaper.homeset.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.home_fragment.*


class HomeFragment : Fragment() {

    private val viewModel by activityViewModels<MainViewModel>()
    private lateinit var adapter: AdapterHome

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = AdapterHome()
        val layoutManager = getLayoutManager()
        rv_list.layoutManager = layoutManager
        rv_list.adapter = adapter
        observeChanges()

        rv_list.addOnScrollListener(object  : PaginationListener(layoutManager) {
            override fun loadMoreItems() {
                viewModel.getPhotoList(Constant.CLIENT_ID)
            }

            override val isLastPage: Boolean
                get() = false
            override val isLoading: Boolean
                get() = false

        });
    }

    private fun observeChanges() {
        viewModel.getPhotoList(Constant.CLIENT_ID).observe(requireActivity(), Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.LOADING -> {

                    }
                    Status.SUCCESS -> {
                        progress_bar.visibility = View.GONE
                        rv_list.visibility = View.VISIBLE
                        adapter.submitList(it.data)
                    }
                    Status.ERROR -> {
                        progress_bar.visibility = View.GONE
                    }
                }
            }
        })
    }



    private fun getLayoutManager() : LinearLayoutManager{
        val layoutManager = GridLayoutManager(context, 2)
        layoutManager.spanSizeLookup = object : SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                // last position
                return if (position == adapter.itemCount - 1) 2 else 1
            }
        }
        return layoutManager
    }
}