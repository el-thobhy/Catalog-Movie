package com.elthobhy.catalogmovie.core.domain.repository

import com.elthobhy.catalogmovie.core.data.Resource
import com.elthobhy.catalogmovie.core.domain.model.DomainModel
import kotlinx.coroutines.flow.Flow


interface RepositoryInterface {
    fun getMovies(): Flow<Resource<List<DomainModel>>>
    fun getTvShow(): Flow<Resource<List<DomainModel>>>
}