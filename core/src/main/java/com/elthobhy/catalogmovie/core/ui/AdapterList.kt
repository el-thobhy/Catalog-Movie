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

    var onItemClick: ((DomainModel) -> Unit)? = null

    inner class ViewHolder(private val binding: ItemListBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: DomainModel?, position: Int) {
            with(binding){
                title.text = item?.title
                posterImage.let {
                    Glide.with(itemView)
                        .load(Constants.IMAGE_LINK + item?.backdrop_path)
                        .into(posterImage)
                }
                tvDate.text = item?.releaseDate
                roundImage.let {
                    Glide.with(itemView)
                        .load(Constants.IMAGE_LINK + item?.posterPath)
                        .into(roundImage)
                }
                tvOriginalTitle.text = item?.original_title
                tvOverview.text = item?.overview
                itemView.setOnClickListener {
                    onItemClick?.invoke(getItem(position))
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(getItem(position), position)
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