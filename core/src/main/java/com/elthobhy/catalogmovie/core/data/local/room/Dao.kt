package com.elthobhy.catalogmovie.core.data.local.room

import androidx.lifecycle.LiveData
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

    @Query("SELECT * FROM entity WHERE isTvShow = 0 AND title LIKE '%' || :search || '%' ")
    fun getSearchMovies(search: String): Flow<List<Entity>>

    @Query("SELECT * FROM entity WHERE isTvShow = 1 AND title LIKE '%' || :search || '%' ")
    fun getSearchTvShow(search: String): Flow<List<Entity>>

    @Query("SELECT * FROM entity WHERE isTvShow = 0 AND isFavorite = 1 AND title LIKE '%' || :search || '%' ")
    fun getSearchFavoriteMovies(search: String): Flow<List<Entity>>

    @Query("SELECT * FROM entity WHERE isTvShow = 1 AND isFavorite = 1 AND title LIKE '%' || :search || '%' ")
    fun getSearchFavoriteTvShow(search: String): Flow<List<Entity>>

    @Query("SELECT * FROM entity WHERE isTvShow = 0 AND isFavorite = 1")
    fun getFavoriteMovie(): Flow<List<Entity>>

    @Query("SELECT * FROM entity WHERE isTvShow = 1 AND isFavorite = 1")
    fun getFavoriteTvShow(): Flow<List<Entity>>

    @Query("SELECT * FROM entity WHERE id = :id")
    fun getDetailById(id: Int): LiveData<Entity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: List<Entity>)

    @Update
    fun updateFavoriteMovie(movie: Entity)
}