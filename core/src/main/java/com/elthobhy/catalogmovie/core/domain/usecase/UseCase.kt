package com.elthobhy.catalogmovie.core.domain.usecase

import com.elthobhy.catalogmovie.core.data.Resource
import com.elthobhy.catalogmovie.core.domain.model.DomainModel
import kotlinx.coroutines.flow.Flow

interface UseCase {
    fun getMovies(): Flow<Resource<List<DomainModel>>>
    fun getTvShow(): Flow<Resource<List<DomainModel>>>
    fun getFavoriteMovie(): Flow<List<DomainModel>>
    fun getFavoriteTvShow(): Flow<List<DomainModel>>
    fun setFavoriteMovie(movie: DomainModel, state: Boolean)
}