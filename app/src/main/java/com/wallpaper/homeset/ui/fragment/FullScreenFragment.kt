package com.wallpaper.homeset.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.wallpaper.homeset.R
import com.wallpaper.homeset.model.FeatureModel
import com.wallpaper.homeset.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_full_screen.*

class FullScreenFragment : Fragment() {

    private val viewModel by activityViewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_full_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fromBundle = FullScreenFragmentArgs.fromBundle(requireArguments())

        val photoData = fromBundle.photoData
        set_wallpaper_btn.setOnClickListener {
            viewModel.setWallpaper(photoData)
        }

        viewModel.setWallpaper.observe(requireActivity(), Observer {
            when(it.action) {
                FeatureModel.Action.PROGRESS -> {
                    Log.d("PROGRESS", "" + it.show)
                }

                FeatureModel.Action.TOAST -> {
                    Toast.makeText(context, it.msg, Toast.LENGTH_SHORT).show()
                }
            }
        })

        Glide.with(view.context)
            .load(fromBundle.imageUrl)
            .into(iv_wallpaper)
    }
}