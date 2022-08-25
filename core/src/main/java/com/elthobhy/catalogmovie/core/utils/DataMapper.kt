package com.elthobhy.catalogmovie.core.utils

import com.elthobhy.catalogmovie.core.data.local.entity.Entity
import com.elthobhy.catalogmovie.core.data.remote.response.MovieResponseItem
import com.elthobhy.catalogmovie.core.domain.model.DomainModel

object DataMapper {
    fun mapResponseToEntity(input: List<MovieResponseItem>): List<Entity> {
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
                originalTitle = it.originalTitle,
                backdrop_path = it.backdrop_path,
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
                originalLanguage = it.originalLanguage,
            )
            output.add(list)
        }
        return output
    }
}