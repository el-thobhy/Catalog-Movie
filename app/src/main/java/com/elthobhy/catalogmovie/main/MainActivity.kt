package com.elthobhy.catalogmovie.main

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.elthobhy.catalogmovie.R
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
                2 -> navigationToFavorite()
            }
        }
    }

    private fun navigationToFavorite() {
        val fragment = featureFragment()
        if (fragment != null) {
            navigationChange(fragment)
        }
    }

    private fun featureFragment(): Fragment? {
        return try {
            Class.forName("com.elthobhy.catalogmovie.favorite.FavoriteFragment")
                .newInstance() as Fragment
        } catch (e: Exception) {
            Toast.makeText(this, getString(R.string.not_found_module), Toast.LENGTH_SHORT).show()
            null
        }
    }

    private fun navigationChange(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container_fragment, fragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .commit()
    }
}