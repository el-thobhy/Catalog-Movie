package com.elthobhy.catalogmovie.core.data.remote.networking

import com.elthobhy.catalogmovie.core.data.remote.response.MovieResponse
import com.elthobhy.catalogmovie.core.data.remote.response.TvShowResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    suspend fun getMovies(
        @Query("api_key") apiKey: String,
    ): MovieResponse

    @GET("tv/popular")
    suspend fun getTvShows(
        @Query("api_key") apiKey: String
    ): TvShowResponse

}