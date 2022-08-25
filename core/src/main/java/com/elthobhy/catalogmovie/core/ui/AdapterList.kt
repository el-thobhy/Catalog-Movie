package com.elthobhy.catalogmovie.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.elthobhy.catalogmovie.core.databinding.ItemListBinding
import com.elthobhy.catalogmovie.core.domain.model.DomainModel
import com.elthobhy.catalogmovie.core.utils.Constants

class AdapterList: ListAdapter<DomainModel, AdapterList.ViewHolder>(DIFF_CALLBACK) {

    inner class ViewHolder(private val binding: ItemListBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: DomainModel?) {
            with(binding){
                title.text = item?.title
                roundImage.let {
                    Glide.with(itemView)
                        .load(Constants.IMAGE_LINK + item?.backdrop_path)
                        .into(roundImage)
                }
                tvDate.text = item?.releaseDate
                posterImage.let {
                    Glide.with(itemView)
                        .load(Constants.IMAGE_LINK + item?.posterPath)
                        .into(posterImage)
                }
                tvOriginalTitle.text = item?.original_title
                tvOverview.text = item?.overview
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(getItem(position))
    }
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DomainModel>() {
            override fun areItemsTheSame(oldItem: DomainModel, newItem: DomainModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: DomainModel, newItem: DomainModel): Boolean {
                return oldItem == newItem
            }
        }
    }
}