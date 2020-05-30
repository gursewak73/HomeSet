package com.wallpaper.homeset.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.wallpaper.homeset.R
import kotlinx.android.synthetic.main.fragment_full_screen_view.*

class FullScreenViewFragment : Fragment() {

    companion object {
        const val IMAGE_URL = ""

        fun newInstance(imageUrl : String) : FullScreenViewFragment {
            val fragment = FullScreenViewFragment()
            val bundle = Bundle()
            bundle.putString(IMAGE_URL, imageUrl)
            fragment.arguments = bundle
            return fragment
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_full_screen_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString(IMAGE_URL)?.let {
            Glide.with(view.context)
                .load(it)
                .into(iv_wallpaper)
            progress_bar.visibility = View.GONE
            iv_wallpaper.visibility = View.VISIBLE
        }
    }
}