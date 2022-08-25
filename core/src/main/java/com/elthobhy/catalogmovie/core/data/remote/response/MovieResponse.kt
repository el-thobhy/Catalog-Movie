package com.elthobhy.catalogmovie.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(

	@field:SerializedName("results")
	val results: List<MovieResponseItem>
)

data class MovieResponseItem(

	@field:SerializedName("overview")
	val overview: String,

	@field:SerializedName("original_language")
	val originalLanguage: String,

	@field:SerializedName("release_date")
	val releaseDate: String,

	@field:SerializedName("popularity")
	val popularity: Double,

	@field:SerializedName("vote_average")
	val voteAverage: Double,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("vote_count")
	val voteCount: Int,

	@field:SerializedName("poster_path")
	val posterPath: String,

	@field:SerializedName("backdrop_path")
	val backdrop_path: String,

	@field:SerializedName("original_title")
	val originalTitle: String,




	)
