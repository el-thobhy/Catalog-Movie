package com.elthobhy.catalogmovie.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.elthobhy.catalogmovie.di.favoriteModule
import com.elthobhy.catalogmovie.favorite.databinding.FragmentFavoriteBinding
import com.elthobhy.catalogmovie.favorite.viewpageradapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules


@FlowPreview
@ExperimentalCoroutinesApi
class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding as FragmentFavoriteBinding
    private lateinit var viewPager2: ViewPager2
    private lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        viewPagerAdapter = ViewPagerAdapter(requireActivity(), lifecycle)
        viewPager2 = binding.viewPager
        viewPager2.adapter = viewPagerAdapter

        val tabTitle = arrayOf(
            R.string.movies,
            R.string.tv_show,
        )
        val tabLayout = binding.tabLayout
        TabLayoutMediator(tabLayout, binding.viewPager) { tab, position ->
            tab.text = context?.resources?.getString(tabTitle[position])
        }.attach()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadKoinModules(favoriteModule)
    }

    override fun onDestroyView() {
        viewPager2.adapter = null
        _binding = null
        super.onDestroyView()
    }
}