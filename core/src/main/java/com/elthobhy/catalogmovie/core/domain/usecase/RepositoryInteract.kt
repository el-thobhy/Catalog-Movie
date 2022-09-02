package com.elthobhy.catalogmovie.core.domain.usecase

import androidx.lifecycle.LiveData
import com.elthobhy.catalogmovie.core.data.Resource
import com.elthobhy.catalogmovie.core.domain.model.DomainModel
import com.elthobhy.catalogmovie.core.domain.repository.RepositoryInterface
import kotlinx.coroutines.flow.Flow

class RepositoryInteract(private val repository: RepositoryInterface) : UseCase {
    override fun getMovies(): Flow<Resource<List<DomainModel>>> = repository.getMovies()
    override fun getTvShow(): Flow<Resource<List<DomainModel>>> = repository.getTvShow()
    override fun getSearchMovies(search: String): Flow<List<DomainModel>> =
        repository.getSearchMovies(search)

    override fun getSearchTvShows(search: String): Flow<List<DomainModel>> =
        repository.getSearchTvShow(search)

    override fun getSearchFavoriteMovies(search: String): Flow<List<DomainModel>> =
        repository.getSearchFavoriteMovies(search)

    override fun getSearchFavoriteTvShows(search: String): Flow<List<DomainModel>> =
        repository.getSearchFavoriteTvShow(search)

    override fun getFavoriteMovie(): Flow<List<DomainModel>> = repository.getFavoriteMovies()
    override fun getFavoriteTvShow(): Flow<List<DomainModel>> = repository.getFavoriteTvShow()
    override fun getDetailById(id: Int): LiveData<DomainModel> =
        repository.getDetailById(id)


    override fun setFavoriteMovie(movie: DomainModel, state: Boolean) =
        repository.setFavoriteMovies(movie, state)

}