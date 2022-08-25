package com.elthobhy.catalogmovie.detail

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.elthobhy.catalogmovie.R
import com.elthobhy.catalogmovie.core.domain.model.DomainModel
import com.elthobhy.catalogmovie.core.utils.Constants
import com.elthobhy.catalogmovie.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

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