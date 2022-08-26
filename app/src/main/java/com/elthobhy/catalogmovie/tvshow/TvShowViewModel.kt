package com.elthobhy.catalogmovie.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.elthobhy.catalogmovie.core.data.Resource
import com.elthobhy.catalogmovie.core.domain.model.DomainModel
import com.elthobhy.catalogmovie.core.domain.usecase.UseCase

class TvShowViewModel(private val useCase: UseCase) : ViewModel() {
    fun getTvShow(): LiveData<Resource<List<DomainModel>>> = useCase.getTvShow().asLiveData()
}