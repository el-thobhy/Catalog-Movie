package com.elthobhy.catalogmovie.core.domain.repository

import androidx.lifecycle.LiveData
import com.elthobhy.catalogmovie.core.data.Resource
import com.elthobhy.catalogmovie.core.domain.model.DomainModel
import kotlinx.coroutines.flow.Flow


interface RepositoryInterface {
    fun getMovies(): Flow<Resource<List<DomainModel>>>
    fun getTvShow(): Flow<Resource<List<DomainModel>>>
    fun getSearchMovies(search: String): Flow<List<DomainModel>>
    fun getSearchTvShow(search: String): Flow<List<DomainModel>>
    fun getSearchFavoriteMovies(search: String): Flow<List<DomainModel>>
    fun getSearchFavoriteTvShow(search: String): Flow<List<DomainModel>>
    fun getDetailById(id: Int): LiveData<DomainModel>
    fun getFavoriteMovies(): Flow<List<DomainModel>>
    fun getFavoriteTvShow(): Flow<List<DomainModel>>
    fun setFavoriteMovies(movie: DomainModel, state: Boolean)
}