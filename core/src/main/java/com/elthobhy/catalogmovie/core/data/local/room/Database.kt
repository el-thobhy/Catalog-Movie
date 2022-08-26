package com.elthobhy.catalogmovie.core.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.elthobhy.catalogmovie.core.data.local.entity.Entity

@Database(entities = [Entity::class], version = 1, exportSchema = false)
abstract class Database : RoomDatabase() {
    abstract fun dao(): Dao
}