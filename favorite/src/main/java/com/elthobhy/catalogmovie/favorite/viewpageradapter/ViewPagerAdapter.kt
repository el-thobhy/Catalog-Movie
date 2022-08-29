package com.elthobhy.catalogmovie.favorite.viewpageradapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.elthobhy.catalogmovie.core.utils.Constants
import com.elthobhy.catalogmovie.favorite.moviestv.FavoriteMovieTvFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@FlowPreview
@ExperimentalCoroutinesApi
class ViewPagerAdapter(fa: FragmentActivity, lifecycle: Lifecycle) : FragmentStateAdapter(fa.supportFragmentManager, lifecycle) {
    override fun getItemCount(): Int = Constants.NUM_PAGE

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = FavoriteMovieTvFragment(true)
            1 -> fragment = FavoriteMovieTvFragment(false)
        }
        return fragment as Fragment
    }
}