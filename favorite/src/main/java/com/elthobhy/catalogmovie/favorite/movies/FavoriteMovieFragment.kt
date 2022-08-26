package com.elthobhy.catalogmovie.favorite.movies

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.elthobhy.catalogmovie.core.domain.model.DomainModel
import com.elthobhy.catalogmovie.core.ui.AdapterList
import com.elthobhy.catalogmovie.core.utils.Constants
import com.elthobhy.catalogmovie.detail.DetailActivity
import com.elthobhy.catalogmovie.favorite.FavoriteViewModel
import com.elthobhy.catalogmovie.favorite.databinding.FragmentFavoriteMovieBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class FavoriteMovieFragment : Fragment() {

    private var _binding: FragmentFavoriteMovieBinding? = null
    private val binding get() = _binding as FragmentFavoriteMovieBinding
    private val favoriteViewModel: FavoriteViewModel by viewModel()
    private lateinit var adapterList: AdapterList

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapterList = AdapterList()
        setList()
        binding.rvFavoriteMovies.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
            adapter = adapterList
        }
        adapterList.onItemClick = {
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(Constants.DATA, it)
            startActivity(intent)
        }
    }

    private fun setList() {
        favoriteViewModel.getFavoriteMovie().observe(viewLifecycleOwner) { movies ->
            adapterList.submitList(movies)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}