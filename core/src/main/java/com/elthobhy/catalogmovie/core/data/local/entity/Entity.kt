package com.elthobhy.catalogmovie.core.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "entity")
data class Entity(
    @ColumnInfo(name = "overview")
    val overview: String? = null,

    @ColumnInfo(name = "originalLanguage")
    val originalLanguage: String? = null,

    @ColumnInfo(name = "backdrop_path")
    val backdrop_path: String? = null,

    @ColumnInfo(name = "releaseDate")
    val releaseDate: String? = null,

    @ColumnInfo(name = "popularity")
    val popularity: Double? = null,

    @ColumnInfo(name = "voteAverage")
    val voteAverage: Double? = null,

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "title")
    val title: String? = null,

    @ColumnInfo(name = "voteCount")
    val voteCount: Int? = null,

    @ColumnInfo(name = "posterPath")
    val posterPath: String? = null,

    @ColumnInfo(name = "original_title")
    val originalTitle: String? = null,

    @ColumnInfo(name = "isTvShow")
    var isTvShow: Boolean = false,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
)