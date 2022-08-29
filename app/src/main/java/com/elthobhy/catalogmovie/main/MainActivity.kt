package com.elthobhy.catalogmovie.main

import android.app.Activity
import android.os.Bundle
import android.util.ArrayMap
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.transition.Transition
import androidx.transition.TransitionManager
import com.elthobhy.catalogmovie.R
import com.elthobhy.catalogmovie.databinding.ActivityMainBinding
import com.elthobhy.catalogmovie.moviestv.MovieTvFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import java.lang.ref.WeakReference

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

    @Suppress("UNCHECKED_CAST")
    private fun removeActivityFromTransitionManager(activity: Activity) {
        val transition = TransitionManager::class.java
        try {
            val field = transition.getDeclaredField("sRunningTransitions")
            field.isAccessible = true
            val runningTransitions: ThreadLocal<WeakReference<ArrayMap<ViewGroup, ArrayList<Transition>>>> =
                field.get(transition) as ThreadLocal<WeakReference<ArrayMap<ViewGroup, ArrayList<Transition>>>>
            if (runningTransitions.get() == null || runningTransitions.get()?.get() == null) {
                return
            }
            val map = runningTransitions.get()?.get()
            val decorView = activity.window.decorView
            if (map != null) {
                if (map.containsKey(decorView)) {
                    map.remove(decorView)
                }
            }

        } catch (e: NoSuchFieldException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        removeActivityFromTransitionManager(this)
    }
}