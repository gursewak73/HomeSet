package com.wallpaper.homeset.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.wallpaper.homeset.entity.EntityPhoto
import com.wallpaper.homeset.ui.fragment.FullScreenViewFragment

class AdapterFullScreenView(fm: FragmentManager, private val photoArrayList: Array<EntityPhoto>) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        var thumbUrl : String? = photoArrayList[position].entityUrl?.regular
        if (thumbUrl == null) {
            thumbUrl = photoArrayList[position].coverPhoto?.url?.regular!!
        }
        return FullScreenViewFragment.newInstance(thumbUrl)
    }

    override fun getCount(): Int {
        return photoArrayList.size
    }
}
