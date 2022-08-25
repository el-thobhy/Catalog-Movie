package com.elthobhy.catalogmovie.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class TvShowResponse(

	@field:SerializedName("results")
	val results: List<TvShowResponseItem>
)

data class TvShowResponseItem(

	@field:SerializedName("first_air_date")
	val firstAirDate: String,

	@field:SerializedName("overview")
	val overview: String,

	@field:SerializedName("backdrop_path")
	val backdropPath: String,

	@field:SerializedName("original_language")
	val originalLanguage: String,

	@field:SerializedName("popularity")
	val popularity: Double,

	@field:SerializedName("original_name")
	val originalTitle: String,

	@field:SerializedName("vote_average")
	val voteAverage: Double,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("vote_count")
	val voteCount: Int,

	@field:SerializedName("poster_path")
	val posterPath: String,

	)
