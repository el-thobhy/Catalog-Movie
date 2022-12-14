package com.elthobhy.catalogmovie.core.domain.usecase

import androidx.lifecycle.LiveData
import com.elthobhy.catalogmovie.core.data.Resource
import com.elthobhy.catalogmovie.core.domain.model.DomainModel
import kotlinx.coroutines.flow.Flow

interface UseCase {
    fun getMovies(): Flow<Resource<List<DomainModel>>>
    fun getTvShow(): Flow<Resource<List<DomainModel>>>
    fun getSearchMovies(search: String): Flow<List<DomainModel>>
    fun getSearchTvShows(search: String): Flow<List<DomainModel>>
    fun getSearchFavoriteMovies(search: String): Flow<List<DomainModel>>
    fun getSearchFavoriteTvShows(search: String): Flow<List<DomainModel>>
    fun getFavoriteMovie(): Flow<List<DomainModel>>
    fun getFavoriteTvShow(): Flow<List<DomainModel>>
    fun getDetailById(id: Int): LiveData<DomainModel>
    fun setFavoriteMovie(movie: DomainModel, state: Boolean)
}