package com.elthobhy.catalogmovie.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.elthobhy.catalogmovie.core.data.Resource
import com.elthobhy.catalogmovie.core.domain.model.DomainModel
import com.elthobhy.catalogmovie.core.domain.usecase.UseCase

class MovieViewModel(private val useCase: UseCase) : ViewModel() {
    fun getMovies(): LiveData<Resource<List<DomainModel>>> = useCase.getMovies().asLiveData()
}