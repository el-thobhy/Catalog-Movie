package com.elthobhy.catalogmovie.core.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.elthobhy.catalogmovie.core.data.local.entity.Entity
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Query("SELECT * FROM entity")
    fun getMovies(): Flow<List<Entity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: List<Entity>)
}