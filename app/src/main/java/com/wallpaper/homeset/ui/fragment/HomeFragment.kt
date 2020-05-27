package com.wallpaper.homeset.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.wallpaper.homeset.R
import com.wallpaper.homeset.model.Status
import com.wallpaper.homeset.viewmodel.MainViewModel

class HomeFragment : Fragment() {

    private val viewModel by activityViewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeChanges()
    }

    private fun observeChanges() {
        viewModel.getCollections().observe(requireActivity(), Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.LOADING -> {
                        Log.d("status","LOADING")
                    }
                    Status.SUCCESS -> {
                        Log.d("status","SUCCESS")
                    }
                    Status.ERROR -> {
                        Log.d("status","ERROR")
                    }
                }
            }
        })
    }
}