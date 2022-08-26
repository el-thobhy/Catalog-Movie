package com.elthobhy.catalogmovie.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class TvShowResponse(

    @field:SerializedName("results")
    val results: List<TvShowResponseItem>
)

data class TvShowResponseItem(

    @field:SerializedName("first_air_date")
    val firstAirDate: String? = null,

    @field:SerializedName("overview")
    val overview: String? = null,

    @field:SerializedName("backdrop_path")
    val backdropPath: String? = null,

    @field:SerializedName("original_language")
    val originalLanguage: String? = null,

    @field:SerializedName("popularity")
    val popularity: Double? = null,

    @field:SerializedName("original_name")
    val originalTitle: String? = null,

    @field:SerializedName("vote_average")
    val voteAverage: Double? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("vote_count")
    val voteCount: Int? = null,

    @field:SerializedName("poster_path")
    val posterPath: String? = null,

    )
