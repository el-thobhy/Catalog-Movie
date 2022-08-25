package com.elthobhy.catalogmovie.tvshow

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.elthobhy.catalogmovie.R
import com.elthobhy.catalogmovie.core.data.Resource
import com.elthobhy.catalogmovie.core.domain.model.DomainModel
import com.elthobhy.catalogmovie.core.ui.AdapterList
import com.elthobhy.catalogmovie.core.utils.Constants
import com.elthobhy.catalogmovie.databinding.FragmentTvshowBinding
import com.elthobhy.catalogmovie.detail.DetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class TvshowFragment : Fragment() {

    private var _binding: FragmentTvshowBinding? = null
    private val binding get() = _binding as FragmentTvshowBinding
    private lateinit var adapterList: AdapterList
    private val tvShowViewModel: TvShowViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTvshowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapterList = AdapterList()
        setList()
        with(binding.rvShow){
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
        tvShowViewModel.getTvShow().observe(viewLifecycleOwner){
            if(it != null){
                when (it){
                    is Resource.Loading -> {

                    }
                    is Resource.Success -> {
                        adapterList.submitList(it.data)
                    }
                    is Resource.Error -> {
                        Log.e("movieFragment", "setList: ${it.message}" )
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}