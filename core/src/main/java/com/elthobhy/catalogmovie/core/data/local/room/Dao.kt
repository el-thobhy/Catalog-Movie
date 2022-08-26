package com.elthobhy.catalogmovie.core.data.local.room

import androidx.room.*
import androidx.room.Dao
import com.elthobhy.catalogmovie.core.data.local.entity.Entity
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Query("SELECT * FROM entity WHERE isTvShow = 0")
    fun getMovies(): Flow<List<Entity>>

    @Query("SELECT * FROM entity WHERE isTvShow = 1")
    fun getTvShow(): Flow<List<Entity>>

    @Query("SELECT * FROM entity WHERE isTvShow = 0 AND isFavorite = 1")
    fun getFavoriteMovie(): Flow<List<Entity>>

    @Query("SELECT * FROM entity WHERE isFavorite = 1 AND  isTvShow = 1 ")
    fun getFavoriteTvShow(): Flow<List<Entity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: List<Entity>)

    @Update
    fun updateFavoriteMovie(movie: Entity)
}