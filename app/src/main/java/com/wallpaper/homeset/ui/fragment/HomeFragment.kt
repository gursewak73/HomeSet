package com.wallpaper.homeset.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wallpaper.homeset.databinding.HomeFragmentNewBinding
import com.wallpaper.homeset.network.model.Result
import com.wallpaper.homeset.ui.TheApplication
import com.wallpaper.homeset.ui.activity.FullScreenActivityNew
import com.wallpaper.homeset.ui.adapter.AdapterHome
import com.wallpaper.homeset.viewmodel.MainViewModel
import javax.inject.Inject

class HomeFragment : Fragment() {

    private lateinit var binding: HomeFragmentNewBinding
    private lateinit var adapter: AdapterHome
    private var loadMoreItems = false

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(
            this, viewModelFactory
        )
            .get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireActivity().application as TheApplication).appComponent.inject(this)
        binding = HomeFragmentNewBinding.inflate(inflater, container, false);
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeList()
        observeChanges()
    }

    private fun initializeList() {
        adapter = AdapterHome(viewModel)
        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvList.layoutManager = layoutManager
        binding.rvList.adapter = adapter
        addOnScrollListenerForPagination(layoutManager)
    }

    private fun addOnScrollListenerForPagination(layoutManager: LinearLayoutManager) {
        binding.rvList.addOnScrollListener(object : RecyclerView.OnScrollListener() {

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
                when (resource) {
                    is Result.Success -> {
                        binding.progressBar.visibility = View.GONE
                        binding.rvList.visibility = View.VISIBLE
                        adapter.submitList(resource.data)
                        loadMoreItems = true
                    }
                    is Result.Error -> {
                        if (adapter.currentList.size == 0) {
                            binding.tvNoInternet.visibility = View.VISIBLE
                            binding.rvList.visibility = View.GONE
                        }
                        binding.progressBar.visibility = View.GONE
                    }
                }
            }
        })

        /*viewModel.getCollectionData.observe(this, Observer {

            when (it) {
                is com.wallpaper.homeset.network.model.Result.Success -> {
                    it.data.let { list ->
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
                }
            }
        })*/

        /*viewModel.getCollectionPhotos.observe(this, Observer {
            it.data.let { list ->
                adapter.submitListForCollection(list)
            }
        })*/

        viewModel.openFullScreen.observe(requireActivity(), Observer {
            val currentList = adapter.currentList
            val intent = Intent(requireContext(), FullScreenActivityNew::class.java)
            startActivity(intent.apply {
                putExtra(FullScreenActivityNew.POSITION, it)
                putParcelableArrayListExtra(
                    FullScreenActivityNew.PHOTO_LIST,
                    java.util.ArrayList(currentList)
                )
            })
        })
    }

    companion object {

        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }
}