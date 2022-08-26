package com.elthobhy.catalogmovie.favorite.tvshow

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.elthobhy.catalogmovie.core.ui.AdapterList
import com.elthobhy.catalogmovie.core.utils.Constants
import com.elthobhy.catalogmovie.detail.DetailActivity
import com.elthobhy.catalogmovie.favorite.FavoriteViewModel
import com.elthobhy.catalogmovie.favorite.R
import com.elthobhy.catalogmovie.favorite.databinding.FragmentFavoriteTvShowBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteTvShowFragment : Fragment() {

    private var _binding: FragmentFavoriteTvShowBinding? = null
    private val binding get() = _binding as FragmentFavoriteTvShowBinding
    private lateinit var adapterList: AdapterList
    private val favoriteViewModel: FavoriteViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteTvShowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapterList = AdapterList()
        binding.rvFavoriteTvShow.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
            adapter = adapterList
        }
        adapterList.onItemClick = {
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(Constants.DATA, it)
            startActivity(intent)
        }
        setList()
    }

    private fun setList() {
        favoriteViewModel.getFavoriteTvShow().observe(viewLifecycleOwner){
            adapterList.submitList(it)
        }
    }

}