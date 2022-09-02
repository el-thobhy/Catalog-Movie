package com.elthobhy.catalogmovie.favorite.moviestv

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.elthobhy.catalogmovie.core.databinding.ItemListBinding
import com.elthobhy.catalogmovie.core.domain.model.DomainModel
import com.elthobhy.catalogmovie.core.ui.AdapterList
import com.elthobhy.catalogmovie.core.utils.Constants
import com.elthobhy.catalogmovie.detail.DetailActivity
import com.elthobhy.catalogmovie.favorite.FavoriteViewModel
import com.elthobhy.catalogmovie.favorite.databinding.FragmentFavoriteMovieBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.androidx.viewmodel.ext.android.viewModel


@FlowPreview
@ExperimentalCoroutinesApi
class FavoriteMovieTvFragment(private var isMovie: Boolean = true) : Fragment() {

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
        binding.notFound.visibility = View.GONE
        binding.emptyText.visibility = View.GONE
        setList()
        showRv()
    }

    private val observerMovieTvShow = Observer<List<DomainModel>> {
        if (it.isNullOrEmpty()) {
            binding.notFound.visibility = View.VISIBLE
            binding.emptyText.visibility = View.VISIBLE
        } else {
            binding.notFound.visibility = View.GONE
            binding.emptyText.visibility = View.GONE
        }
        adapterList.submitList(it)
    }

    private fun showRv() {
        binding.rvFavoriteMovies.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
            adapter = adapterList
        }
        adapterList.setOnItemClickCallback(object : AdapterList.OnItemClickCallback {
            override fun onItemClicked(data: DomainModel, binding: ItemListBinding) {
                setDetail(data, binding)
            }
        })
    }

    internal fun setDetail(data: DomainModel, itemBinding: ItemListBinding) {
        itemBinding.apply {
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(Constants.DATA, data)
            startActivity(intent)
        }
    }

    private fun setList() {
        if (isMovie) {
            favoriteViewModel.getFavoriteMovie().observe(viewLifecycleOwner, observerMovieTvShow)
        } else {
            favoriteViewModel.getFavoriteTvShow().observe(viewLifecycleOwner, observerMovieTvShow)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}