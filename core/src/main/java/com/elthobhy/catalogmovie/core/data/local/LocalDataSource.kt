package com.elthobhy.catalogmovie.core.data.local

import com.elthobhy.catalogmovie.core.data.local.entity.Entity
import com.elthobhy.catalogmovie.core.data.local.room.Dao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val dao: Dao) {
    fun getMovies(): Flow<List<Entity>> = dao.getMovies()
    suspend fun insert(entity: List<Entity>) = dao.insert(entity)
}