package com.elthobhy.catalogmovie.core.data.remote

import com.elthobhy.catalogmovie.core.data.remote.networking.ApiConfig
import com.elthobhy.catalogmovie.core.data.remote.networking.ApiResponse
import com.elthobhy.catalogmovie.core.data.remote.networking.ApiService
import com.elthobhy.catalogmovie.core.data.remote.response.MovieResponse
import com.elthobhy.catalogmovie.core.data.remote.response.MovieResponseItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception

class RemoteDataSource {
    private val apiKey = "dccd44dbfc2c7c82f7c3b46080a96b16"

    suspend fun getMovies(): Flow<ApiResponse<List<MovieResponseItem>>> {
       return flow {
            try {
                val response = ApiConfig.getApiService().getMovies(apiKey)
                val list = response.results
                if(list.isNotEmpty()){
                    emit(ApiResponse.Success(list))
                }else{
                    emit(ApiResponse.Empty)
                }
            }catch (e: Exception){
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}