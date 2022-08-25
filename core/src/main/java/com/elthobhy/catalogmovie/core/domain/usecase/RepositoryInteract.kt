package com.elthobhy.catalogmovie.core.domain.usecase

import com.elthobhy.catalogmovie.core.data.Resource
import com.elthobhy.catalogmovie.core.domain.model.DomainModel
import com.elthobhy.catalogmovie.core.domain.repository.RepositoryInterface
import kotlinx.coroutines.flow.Flow

class RepositoryInteract(private val repository: RepositoryInterface): UseCase {
    override fun getMovies(): Flow<Resource<List<DomainModel>>> = repository.getMovies()
    override fun getTvShow(): Flow<Resource<List<DomainModel>>> = repository.getTvShow()
    override fun getFavoriteMovie(): Flow<List<DomainModel>> = repository.getFavoriteMovies()
    override fun setFavoriteMovie(movie: DomainModel, state: Boolean) = repository.setFavoriteMovies(movie, state)

}