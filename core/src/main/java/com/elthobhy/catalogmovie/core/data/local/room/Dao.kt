package com.elthobhy.catalogmovie.core.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.elthobhy.catalogmovie.core.data.local.entity.Entity
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Query("SELECT * FROM entity WHERE isTvShow = 0")
    fun getMovies(): Flow<List<Entity>>

    @Query("SELECT * FROM entity WHERE isTvShow = 1")
    fun getTvShow(): Flow<List<Entity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: List<Entity>)
}