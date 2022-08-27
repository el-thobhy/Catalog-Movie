package com.elthobhy.catalogmovie.favorite.movies

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.elthobhy.catalogmovie.R
import com.elthobhy.catalogmovie.core.databinding.ItemListBinding
import com.elthobhy.catalogmovie.core.domain.model.DomainModel
import com.elthobhy.catalogmovie.core.ui.AdapterList
import com.elthobhy.catalogmovie.core.utils.Constants
import com.elthobhy.catalogmovie.detail.DetailActivity
import com.elthobhy.catalogmovie.favorite.FavoriteViewModel
import com.elthobhy.catalogmovie.favorite.databinding.FragmentFavoriteMovieBinding
import com.elthobhy.catalogmovie.main.MainActivity
import com.elthobhy.catalogmovie.main.SearchViewModel
import com.miguelcatalan.materialsearchview.MaterialSearchView
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.androidx.viewmodel.ext.android.viewModel


@FlowPreview
@ExperimentalCoroutinesApi
class FavoriteMovieFragment : Fragment() {

    private var _binding: FragmentFavoriteMovieBinding? = null
    private val binding get() = _binding as FragmentFavoriteMovieBinding
    private val favoriteViewModel: FavoriteViewModel by viewModel()
    private lateinit var adapterList: AdapterList
    private val searchViewModel: SearchViewModel by viewModel()
    private lateinit var searchView: MaterialSearchView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteMovieBinding.inflate(inflater, container, false)
        initToolbar()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapterList = AdapterList()
        binding.imageEmpty.visibility = View.GONE
        binding.emptyText.visibility = View.GONE
        setList()
        showRv()
        searchList()
    }

    private fun initToolbar() {
        val toolbar: Toolbar = activity?.findViewById<View>(R.id.toolbar) as Toolbar
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        setOptionMenu()
        searchView = (activity as MainActivity).findViewById(R.id.search_view)
    }

    private fun setOptionMenu() {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menu.clear()
                menuInflater.inflate(R.menu.search_menu, menu)
                val item = menu.findItem(R.id.action_search)
                searchView.setMenuItem(item)
                searchView.setOnQueryTextListener(object : MaterialSearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        return true
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        newText?.let {
                            searchViewModel.queryChannel.value = it
                        }
                        return true
                    }
                })
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return true
            }

        }, viewLifecycleOwner, lifecycle.currentState)
    }

    private fun searchList() {
        searchViewModel.movieFavoriteResult.observe(viewLifecycleOwner) {
            if (it.isNullOrEmpty()) {
                binding.imageEmpty.visibility = View.VISIBLE
                binding.emptyText.visibility = View.VISIBLE
            } else {
                binding.imageEmpty.visibility = View.GONE
                binding.emptyText.visibility = View.GONE
            }
            adapterList.submitList(it)
        }
        searchView.setOnSearchViewListener(object : MaterialSearchView.SearchViewListener {
            override fun onSearchViewShown() {}

            override fun onSearchViewClosed() {
                setList()
            }

        })
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
            if (movies.isNullOrEmpty()) {
                binding.imageEmpty.visibility = View.VISIBLE
                binding.emptyText.visibility = View.VISIBLE
            } else {
                binding.imageEmpty.visibility = View.GONE
                binding.emptyText.visibility = View.GONE
            }
            adapterList.submitList(movies)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}