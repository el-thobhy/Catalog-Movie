package com.elthobhy.catalogmovie.core.data.local

import com.elthobhy.catalogmovie.core.data.local.entity.Entity
import com.elthobhy.catalogmovie.core.data.local.room.Dao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val dao: Dao) {
    fun getMovies(): Flow<List<Entity>> = dao.getMovies()
    fun getTvShow(): Flow<List<Entity>> = dao.getTvShow()
    suspend fun insert(entity: List<Entity>) = dao.insert(entity)
    fun getFavoriteMovie(): Flow<List<Entity>> = dao.getFavoriteMovie()
    fun getFavoriteTvShow(): Flow<List<Entity>> = dao.getFavoriteTvShow()
    fun setFavoriteMovie(movie: Entity, state: Boolean) {
        movie.isFavorite = state
        dao.updateFavoriteMovie(movie)
    }
}