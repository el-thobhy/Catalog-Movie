package com.elthobhy.catalogmovie.core.data.local

import androidx.lifecycle.LiveData
import com.elthobhy.catalogmovie.core.data.local.entity.Entity
import com.elthobhy.catalogmovie.core.data.local.room.Dao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val dao: Dao) {
    fun getMovies(): Flow<List<Entity>> = dao.getMovies()
    fun getTvShow(): Flow<List<Entity>> = dao.getTvShow()
    fun getSearchMovies(search: String): Flow<List<Entity>> = dao.getSearchMovies(search)
    fun getSearchTvShow(search: String): Flow<List<Entity>> = dao.getSearchTvShow(search)
    fun getSearchFavoriteMovies(search: String): Flow<List<Entity>> =
        dao.getSearchFavoriteMovies(search)

    fun getSearchFavoriteTvShow(search: String): Flow<List<Entity>> =
        dao.getSearchFavoriteTvShow(search)

    fun getDetailById(id: Int): LiveData<Entity> = dao.getDetailById(id)
    suspend fun insert(entity: List<Entity>) = dao.insert(entity)
    fun getFavoriteMovie(): Flow<List<Entity>> = dao.getFavoriteMovie()
    fun getFavoriteTvShow(): Flow<List<Entity>> = dao.getFavoriteTvShow()
    fun setFavoriteMovie(movie: Entity, state: Boolean) {
        movie.isFavorite = state
        dao.updateFavoriteMovie(movie)
    }
}