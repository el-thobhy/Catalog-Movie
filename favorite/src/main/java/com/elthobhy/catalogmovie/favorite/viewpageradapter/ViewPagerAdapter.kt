package com.elthobhy.catalogmovie.favorite.viewpageradapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.elthobhy.catalogmovie.core.utils.Constants
import com.elthobhy.catalogmovie.favorite.movies.FavoriteMovieFragment
import com.elthobhy.catalogmovie.favorite.tvshow.FavoriteTvShowFragment

class ViewPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = Constants.NUM_PAGE

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = FavoriteMovieFragment()
            1 -> fragment = FavoriteTvShowFragment()
        }
        return fragment as Fragment
    }
}