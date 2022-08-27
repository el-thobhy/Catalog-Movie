package com.elthobhy.catalogmovie.core.data.remote

import android.util.Log
import com.elthobhy.catalogmovie.core.BuildConfig
import com.elthobhy.catalogmovie.core.data.remote.networking.ApiConfig
import com.elthobhy.catalogmovie.core.data.remote.networking.ApiResponse
import com.elthobhy.catalogmovie.core.data.remote.response.MovieResponseItem
import com.elthobhy.catalogmovie.core.data.remote.response.TvShowResponseItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource {

    suspend fun getMovies(): Flow<ApiResponse<List<MovieResponseItem>>> {
        return flow {
            try {
                val response = ApiConfig.getApiService().getMovies(BuildConfig.API_KEY)
                val list = response.results
                if (list.isNotEmpty()) {
                    emit(ApiResponse.Success(list))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getTvShow(): Flow<ApiResponse<List<TvShowResponseItem>>> {
        return flow {
            try {
                val response = ApiConfig.getApiService().getTvShows(BuildConfig.API_KEY)
                val list = response.results
                if (list.isNotEmpty()) {
                    emit(ApiResponse.Success(list))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
                Log.e("remote", "getTvShow: ${e.message}")
            }
        }.flowOn(Dispatchers.IO)
    }

}