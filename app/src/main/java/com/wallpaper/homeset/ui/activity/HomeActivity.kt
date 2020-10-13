package com.wallpaper.homeset.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.Fade
import com.google.android.material.chip.Chip
import com.google.android.material.transition.MaterialContainerTransform
import com.wallpaper.homeset.R
import com.wallpaper.homeset.databinding.HomeFragmentBinding
import com.wallpaper.homeset.entity.EntityPhoto
import com.wallpaper.homeset.ui.TheApplication
import com.wallpaper.homeset.ui.adapter.AdapterHome
import com.wallpaper.homeset.ui.fragment.FullScreenViewFragment
import com.wallpaper.homeset.ui.fragment.HomeFragment
import com.wallpaper.homeset.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.home_fragment.*
import javax.inject.Inject


class HomeActivity : AppCompatActivity() {

    private lateinit var binding: HomeFragmentBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(
            this, viewModelFactory
        )
            .get(MainViewModel::class.java)
    }

    private lateinit var adapter: AdapterHome
    private var loadMoreItems = false

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as TheApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.home_fragment)
        // set toolbar title
        binding.toolbar.title = resources.getString(R.string.app_name)
        addHomeFragment()
//        initializeList()
//        observeChanges()

    }

    private fun addHomeFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, HomeFragment.newInstance()).commit()
    }

    fun openFullScreenFragment(imageUrl: String, position : Int, view: ImageView) {
        val newInstance = FullScreenViewFragment.newInstance(imageUrl)
        newInstance.enterTransition = MaterialContainerTransform()
        newInstance.exitTransition = MaterialContainerTransform()
        newInstance.enterTransition = Fade()
        newInstance.exitTransition = Fade()
        supportFragmentManager.beginTransaction()
            .addSharedElement(view, "test")
            .replace(R.id.container, newInstance)
            .addToBackStack(null)
            .commit()
    }

//    private fun initializeList() {
//        adapter = AdapterHome(viewModel)
//        val layoutManager = getLayoutManager()
//        binding.rvList.layoutManager = layoutManager
//        binding.rvList.adapter = adapter
//        addOnScrollListenerForPagination(layoutManager)
//    }

//    private fun addOnScrollListenerForPagination(layoutManager: LinearLayoutManager) {
//        binding.rvList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//                if (viewModel.listType != MainViewModel.LIST_TYPE_ALL) {
//                    loadMoreItems = true
//                    return
//                }
//
//                if (dy > 0) {
//                    val visibleItemCount = layoutManager.childCount
//                    val totalItemCount = layoutManager.itemCount
//                    val pastVisibleItems = layoutManager.findFirstVisibleItemPosition()
//                    if (loadMoreItems) {
//                        if (visibleItemCount + pastVisibleItems >= totalItemCount) {
//                            loadMoreItems = false
//                            viewModel.getPhotoList()
//                        }
//                    }
//                }
//            }
//        })
//    }

//    private fun observeChanges() {
//        viewModel.getData.observe(this, Observer {
//            if (viewModel.listType != MainViewModel.LIST_TYPE_ALL) {
//                return@Observer
//            }
//            it?.let { resource ->
//                when (resource) {
//                    is com.wallpaper.homeset.network.model.Result.Success -> {
//                        binding.progressBar.visibility = View.GONE
//                        binding.rvList.visibility = View.VISIBLE
//                        adapter.submitList(resource.data)
//                        loadMoreItems = true
//                    }
//                    is com.wallpaper.homeset.network.model.Result.Error -> {
//                        if (adapter.currentList.size == 0) {
//                            binding.tvNoInternet.visibility = View.VISIBLE
//                            binding.rvList.visibility = View.GONE
//                        }
//                        binding.progressBar.visibility = View.GONE
//                    }
//                }
//            }
//        })
//
//        viewModel.getCollectionData.observe(this, Observer {
//
//            when (it) {
//                is com.wallpaper.homeset.network.model.Result.Success -> {
//                    it.data.let { list ->
//                        list.forEachIndexed { index, entityPhoto ->
//                            entityPhoto.title?.let { title ->
//                                addChip(entityPhoto, index, index == 0)
//                            }
//                        }
//
//                        cg_collection.setOnCheckedChangeListener { group, checkedId ->
//                            if (checkedId > -1 && group != null && checkedId <= list.size - 1) {
//                                val chip: Chip = group.findViewById(checkedId)
//                                if (chip.text == resources.getString(R.string.all)) {
//                                    // extract photos
//                                    viewModel.getPhotoList()
//                                    return@setOnCheckedChangeListener
//                                }
//                                // hit api for extracting collection photos
//                                list[checkedId].id?.let { id ->
//                                    viewModel.getCollectionPhotos(id)
//                                }
//                            } else {
//                                val chip: Chip = group.findViewById(0)
//                                chip.performClick()
//                                viewModel.getPhotoList()
//                            }
//                        }
//                    }
//                }
//            }
//        })
//
//        viewModel.getCollectionPhotos.observe(this, Observer {
//            it.data.let { list ->
//                adapter.submitListForCollection(list)
//            }
//        })
//
//        viewModel.openFullScreen.observe(this, Observer {
//            val currentList = adapter.currentList
//            val intent = Intent(this, FullScreenActivityNew::class.java)
//            startActivity(intent.apply {
//                putExtra(FullScreenActivityNew.POSITION, it)
//                putParcelableArrayListExtra(
//                    FullScreenActivityNew.PHOTO_LIST,
//                    java.util.ArrayList(currentList)
//                )
//            })
//        })
//    }

//    private fun addChip(photo: EntityPhoto, index: Int, isSelected: Boolean = false) {
//        val chip = LayoutInflater.from(this).inflate(R.layout.layout_chip, null) as Chip
//        chip.apply {
//            text = photo.title
//            id = index
//            tag = index
//            isChecked = isSelected
//            cg_collection.addView(this)
//        }
//    }


//    private fun getLayoutManager(): LinearLayoutManager {
//        return LinearLayoutManager(this)
//    }
}