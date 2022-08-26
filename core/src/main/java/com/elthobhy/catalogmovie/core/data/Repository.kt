package com.elthobhy.catalogmovie.core.data

import com.elthobhy.catalogmovie.core.data.local.LocalDataSource
import com.elthobhy.catalogmovie.core.data.remote.RemoteDataSource
import com.elthobhy.catalogmovie.core.data.remote.networking.ApiResponse
import com.elthobhy.catalogmovie.core.data.remote.response.MovieResponseItem
import com.elthobhy.catalogmovie.core.data.remote.response.TvShowResponseItem
import com.elthobhy.catalogmovie.core.domain.model.DomainModel
import com.elthobhy.catalogmovie.core.domain.repository.RepositoryInterface
import com.elthobhy.catalogmovie.core.utils.AppExecutors
import com.elthobhy.catalogmovie.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class Repository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val appExecutors: AppExecutors
) : RepositoryInterface {
    override fun getMovies(): Flow<Resource<List<DomainModel>>> =
        object : NetworkBoundResource<List<DomainModel>, List<MovieResponseItem>>() {
            override fun loadFromDB(): Flow<List<DomainModel>> {
                return localDataSource.getMovies().map { DataMapper.mapEntityToDomain(it) }
            }

            override fun shouldFetch(data: List<DomainModel>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponseItem>>> {
                return remoteDataSource.getMovies()
            }

            override suspend fun saveCallResult(data: List<MovieResponseItem>) {
                val dataMap = DataMapper.mapMovieResponseToEntity(data)
                return localDataSource.insert(dataMap)
            }
        }.asFlow()

    override fun getTvShow(): Flow<Resource<List<DomainModel>>> =
        object : NetworkBoundResource<List<DomainModel>, List<TvShowResponseItem>>() {
            override fun loadFromDB(): Flow<List<DomainModel>> {
                return localDataSource.getTvShow().map { DataMapper.mapEntityToDomain(it) }
            }

            override fun shouldFetch(data: List<DomainModel>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<TvShowResponseItem>>> {
                return remoteDataSource.getTvShow()
            }

            override suspend fun saveCallResult(data: List<TvShowResponseItem>) {
                val dataMap = DataMapper.mapShowResponseToEntity(data)
                return localDataSource.insert(dataMap)
            }
        }.asFlow()

    override fun getFavoriteMovies(): Flow<List<DomainModel>> {
        return localDataSource.getFavoriteMovie().map {
            DataMapper.mapEntityToDomain(it)
        }
    }

    override fun getFavoriteTvShow(): Flow<List<DomainModel>> {
        return localDataSource.getFavoriteTvShow().map {
            DataMapper.mapEntityToDomain(it)
        }
    }

    override fun setFavoriteMovies(movie: DomainModel, state: Boolean) {
        val entity = DataMapper.mapDomainToEntity(movie)
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(entity, state) }
    }

}