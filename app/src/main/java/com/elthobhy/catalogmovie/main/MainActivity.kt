package com.elthobhy.catalogmovie.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.elthobhy.catalogmovie.R
import com.elthobhy.catalogmovie.databinding.ActivityMainBinding
import com.elthobhy.catalogmovie.movie.MovieFragment
import com.elthobhy.catalogmovie.tvshow.TvshowFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        navigationChange(MovieFragment())
        binding.bottomNav.setNavigationChangeListener { view, position ->
            when (position) {
                0 -> navigationChange(MovieFragment())
                1 -> navigationChange(TvshowFragment())
                2 -> navigationToFavorite()
            }
        }
    }

    private fun navigationToFavorite() {
        val fragment = featureFragment()
        if (fragment != null){
            navigationChange(fragment)
        }
    }

    private fun featureFragment(): Fragment? {
        return try {
            Class.forName("com.elthobhy.catalogmovie.favorite.FavoriteFragment").newInstance() as Fragment
        } catch (e: Exception) {
            Toast.makeText(this, "module not found", Toast.LENGTH_SHORT).show()
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