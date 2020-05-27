package com.wallpaper.homeset.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.wallpaper.homeset.R
import com.wallpaper.homeset.databinding.HomeFragmentBinding

class HomeFragment : Fragment() {

    private lateinit var mRoot: HomeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mRoot = DataBindingUtil.inflate(layoutInflater, R.layout.home_fragment, container, false)
        return mRoot.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}