package com.elthobhy.catalogmovie.detail

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.elthobhy.catalogmovie.R
import com.elthobhy.catalogmovie.core.domain.model.DomainModel
import com.elthobhy.catalogmovie.core.utils.Constants
import com.elthobhy.catalogmovie.databinding.ActivityDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val detailViewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setData()
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.detail)
    }


    private fun setData() {
        val intent = intent.getParcelableExtra<DomainModel>(Constants.DATA)
        if(intent != null){
            showDetail(intent)
        }
    }

    private fun showDetail(intent: DomainModel) {
        with(binding) {
            Glide.with(this@DetailActivity)
                .load(Constants.IMAGE_LINK+ intent.backdrop_path)
                .into(imageBackdrop)
            titleDetail.text = intent.title
            tvReleaseDate.text = intent.releaseDate
            voteCount.text = intent.voteCount.toString()
            popularCount.text = intent.popularity.toString()
            starCount.text = intent.voteAverage.toString()
            overviewDetail.text = intent.overview

            var favorite = intent.favorite
            setFavoriteState(favorite)
            fabFavorite.setOnClickListener {
                favorite = !favorite
                setFavoriteState(favorite)
                insertFavorite(intent,favorite)
            }
        }
    }

    private fun insertFavorite(intent: DomainModel, favorite: Boolean) {
        detailViewModel.setFavoriteMovie(intent, favorite)
    }

    private fun setFavoriteState(favorite: Boolean) {
        if(favorite){
            binding.fabFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_baseline_bookmark_24
                )
            )
        } else{
            binding.fabFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_baseline_bookmark_border_24
                )
            )
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> true
        }
    }
}