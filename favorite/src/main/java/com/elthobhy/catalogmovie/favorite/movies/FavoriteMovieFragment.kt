package com.elthobhy.catalogmovie.favorite.movies

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.elthobhy.catalogmovie.core.databinding.ItemListBinding
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
        adapterList.setOnItemClickCallback(object : AdapterList.OnItemClickCallback {
            override fun onItemClicked(data: DomainModel, binding: ItemListBinding) {
                setDetail(data, binding)
            }
        })
    }

    private fun setDetail(data: DomainModel, itemBinding: ItemListBinding) {
        itemBinding.apply {
            val optionCompat: ActivityOptionsCompat =
                ActivityOptionsCompat.makeSceneTransitionAnimation(
                    context as Activity,
                    Pair(title, "titleTransition"),
                    Pair(tvDate, "dateTransition"),
                    Pair(tvOriginalTitle, "originalTitleTransition"),
                    Pair(tvOverview, "overviewTransition"),
                    Pair(posterImage, "imageTransition")
                )
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(Constants.DATA, data)
            startActivity(intent, optionCompat.toBundle())
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