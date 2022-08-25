package com.elthobhy.catalogmovie.core.domain.repository

import com.elthobhy.catalogmovie.core.data.Resource
import com.elthobhy.catalogmovie.core.data.local.entity.Entity
import com.elthobhy.catalogmovie.core.domain.model.DomainModel
import kotlinx.coroutines.flow.Flow


interface RepositoryInterface {
    fun getMovies(): Flow<Resource<List<DomainModel>>>
    fun getTvShow(): Flow<Resource<List<DomainModel>>>
    fun getFavoriteMovies(): Flow<List<DomainModel>>
    fun setFavoriteMovies(movie: DomainModel, state: Boolean)
}