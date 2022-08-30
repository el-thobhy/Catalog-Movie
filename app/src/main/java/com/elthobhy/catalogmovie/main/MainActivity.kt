package com.elthobhy.catalogmovie.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.elthobhy.catalogmovie.R
import com.elthobhy.catalogmovie.core.utils.removeActivityFromTransitionManager
import com.elthobhy.catalogmovie.databinding.ActivityMainBinding
import com.elthobhy.catalogmovie.moviestv.MovieTvFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@FlowPreview
@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navigationChange(MovieTvFragment(true))
        binding.bottomNav.setNavigationChangeListener { _, position ->
            when (position) {
                0 -> navigationChange(MovieTvFragment(true))
                1 -> navigationChange(MovieTvFragment(false))
                2 -> featureFragment()
            }
        }
    }

    private fun featureFragment() {
        val fragment =
            Class.forName("com.elthobhy.catalogmovie.favorite.FavoriteFragment").newInstance()
        if (fragment is Fragment) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container_fragment, fragment, "dynamic_fragment")
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack("dynamic_fragment")
                .commit()
        }
    }

    private fun navigationChange(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container_fragment, fragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        removeActivityFromTransitionManager(this)
    }
}