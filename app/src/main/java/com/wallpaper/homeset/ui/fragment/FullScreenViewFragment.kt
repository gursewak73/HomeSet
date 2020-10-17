package com.wallpaper.homeset.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.transition.TransitionInflater
import com.wallpaper.homeset.databinding.FragmentFullScreenViewBinding

class FullScreenViewFragment : Fragment() {

    private lateinit var binding : FragmentFullScreenViewBinding

    companion object {
        const val IMAGE_URL = "image_url"
        const val POSITION = "position"

        fun newInstance(imageUrl: String?, position : Int): FullScreenViewFragment {
            val fragment = FullScreenViewFragment()
            val bundle = Bundle()
            bundle.putString(IMAGE_URL, imageUrl)
            bundle.putInt(POSITION, position)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move);
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFullScreenViewBinding.inflate(inflater, container, false);
        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString(IMAGE_URL)?.let {
            binding.imageUrl = it
            binding.position = arguments?.getInt(POSITION)
            binding.executePendingBindings()
        }
    }
}