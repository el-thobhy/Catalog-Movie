package com.elthobhy.catalogmovie.core.utils

import androidx.lifecycle.LiveData
import com.elthobhy.catalogmovie.core.data.local.entity.Entity
import com.elthobhy.catalogmovie.core.data.remote.response.MovieResponseItem
import com.elthobhy.catalogmovie.core.data.remote.response.TvShowResponseItem
import com.elthobhy.catalogmovie.core.domain.model.DomainModel

object DataMapper {
    fun mapMovieResponseToEntity(input: List<MovieResponseItem>): List<Entity> {
        val output = ArrayList<Entity>()
        input.map {
            val list = Entity(
                title = it.title,
                voteCount = it.voteCount,
                voteAverage = it.voteAverage,
                releaseDate = it.releaseDate,
                posterPath = it.posterPath,
                popularity = it.popularity,
                overview = it.overview,
                id = it.id,
                isTvShow = false,
                originalTitle = it.originalTitle,
                backdrop_path = it.backdrop_path,
                originalLanguage = it.originalLanguage,
            )
            output.add(list)
        }
        return output
    }

    fun mapDomainToEntity(input: DomainModel): Entity{
        return Entity(
                title = input.title,
                voteCount = input.voteCount,
                voteAverage = input.voteAverage,
                releaseDate = input.releaseDate,
                posterPath = input.posterPath,
                popularity = input.popularity,
                overview = input.overview,
                id = input.id,
                isTvShow = input.isTvShows,
                originalTitle = input.original_title,
                backdrop_path = input.backdrop_path,
                originalLanguage = input.originalLanguage,
        )
    }

    fun mapShowResponseToEntity(input: List<TvShowResponseItem>): List<Entity> {
        val output = ArrayList<Entity>()
        input.map {
            val list = Entity(
                title = it.name,
                voteCount = it.voteCount,
                voteAverage = it.voteAverage,
                releaseDate = it.firstAirDate,
                posterPath = it.posterPath,
                popularity = it.popularity,
                overview = it.overview,
                id = it.id,
                isTvShow = true,
                originalTitle = it.originalTitle,
                backdrop_path = it.backdropPath,
                originalLanguage = it.originalLanguage,
            )
            output.add(list)
        }
        return output
    }

    fun mapEntityToDomain(input: List<Entity>): List<DomainModel> {
        val output = ArrayList<DomainModel>()
        input.map {
            val list = DomainModel(
                title = it.title,
                backdrop_path = it.backdrop_path,
                voteCount = it.voteCount,
                voteAverage = it.voteAverage,
                releaseDate = it.releaseDate,
                posterPath = it.posterPath,
                original_title = it.originalTitle,
                popularity = it.popularity,
                overview = it.overview,
                id = it.id,
                isTvShows = it.isTvShow,
                favorite = it.isFavorite,
                originalLanguage = it.originalLanguage,
            )
            output.add(list)
        }
        return output
    }
}