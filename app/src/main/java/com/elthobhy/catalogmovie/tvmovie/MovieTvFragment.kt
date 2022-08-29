package com.elthobhy.catalogmovie.tvmovie

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.elthobhy.catalogmovie.R
import com.elthobhy.catalogmovie.core.data.Resource
import com.elthobhy.catalogmovie.core.databinding.ItemListBinding
import com.elthobhy.catalogmovie.core.domain.model.DomainModel
import com.elthobhy.catalogmovie.core.ui.AdapterList
import com.elthobhy.catalogmovie.core.utils.Constants
import com.elthobhy.catalogmovie.core.utils.showDialogError
import com.elthobhy.catalogmovie.core.utils.showDialogLoading
import com.elthobhy.catalogmovie.databinding.FragmentMovieBinding
import com.elthobhy.catalogmovie.detail.DetailActivity
import com.elthobhy.catalogmovie.main.MainActivity
import com.elthobhy.catalogmovie.main.SearchViewModel
import com.miguelcatalan.materialsearchview.MaterialSearchView
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.androidx.viewmodel.ext.android.viewModel

@FlowPreview
@ExperimentalCoroutinesApi
class MovieTvFragment(private val isMovie: Boolean) : Fragment() {

    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding as FragmentMovieBinding
    private val movieTvViewModel: MovieTvViewModel by viewModel()
    internal val searchViewModel: SearchViewModel by viewModel()
    private lateinit var adapterList: AdapterList
    private lateinit var searchView: MaterialSearchView
    private lateinit var dialogError: AlertDialog
    private lateinit var dialogLoading: AlertDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        initToolbar()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapterList = AdapterList()
        dialogError = showDialogError(requireContext())
        dialogLoading = showDialogLoading(requireContext())
        setList()
        searchList()
        showRv()
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

    private fun showRv() {
        with(binding.rvMovies) {
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

    private fun searchList() {
        if(isMovie){
            searchViewModel.movieResult.observe(viewLifecycleOwner, observerSearch)
        }else{
            searchViewModel.tvShowResult.observe(viewLifecycleOwner, observerSearch)
        }
        searchView.setOnSearchViewListener(object : MaterialSearchView.SearchViewListener {
            override fun onSearchViewShown() {}

            override fun onSearchViewClosed() {
                setList()
            }

        })
    }
    private val observerSearch = Observer<List<DomainModel>>{ adapterList.submitList(it) }

    internal fun setList() {
        if (isMovie) {
            movieTvViewModel.getMovies().observe(viewLifecycleOwner, observerMovieTvShow)
        } else {
            movieTvViewModel.getTvShow().observe(viewLifecycleOwner, observerMovieTvShow)
        }
    }
    private val observerMovieTvShow = Observer<Resource<List<DomainModel>>>{
        if (it != null) {
            when (it) {
                is Resource.Loading -> {
                    dialogLoading.show()
                }
                is Resource.Success -> {
                    dialogLoading.dismiss()
                    adapterList.submitList(it.data)
                }
                is Resource.Error -> {
                    dialogError = showDialogError(requireContext(), it.message)
                    dialogError.show()
                    dialogLoading.dismiss()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        sharedElementEnterTransition = null
        sharedElementReturnTransition = null
        dialogLoading.dismiss()
        dialogError.dismiss()
    }

}