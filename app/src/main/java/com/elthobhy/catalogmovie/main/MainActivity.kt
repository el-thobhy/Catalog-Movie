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
        navigationChange(MovieTvFragment())
        binding.bottomNav.setNavigationChangeListener { _, position ->
            setLayout(position)
        }
    }

    private fun setLayout(position: Int) {
        when (position) {
            0 -> navigationChange(MovieTvFragment(true))
            1 -> navigationChange(MovieTvFragment(false))
            2 -> navigationChange(featureFragment())
        }
    }

    private fun featureFragment(): Fragment? {
        return try {
            Class.forName("com.elthobhy.catalogmovie.favorite.FavoriteFragment")
                .newInstance() as Fragment
        } catch (e: Exception) {
            Toast.makeText(this, "Module not found", Toast.LENGTH_SHORT).show()
            null
        }
    }

    private fun navigationChange(fragment: Fragment?) {
        if (fragment != null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container_fragment, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit()
        }
    }

    override fun onResume() {
        super.onResume()
        setLayout(binding.bottomNav.currentActiveItemPosition)
    }

}