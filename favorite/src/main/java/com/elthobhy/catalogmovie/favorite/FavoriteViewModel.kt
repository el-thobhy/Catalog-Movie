package com.elthobhy.catalogmovie.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.elthobhy.catalogmovie.core.domain.model.DomainModel
import com.elthobhy.catalogmovie.core.domain.usecase.UseCase

class FavoriteViewModel(private val useCase: UseCase): ViewModel() {
    fun getFavoriteMovie(): LiveData<List<DomainModel>> = useCase.getFavoriteMovie().asLiveData()
    fun getFavoriteTvShow(): LiveData<List<DomainModel>> = useCase.getFavoriteTvShow().asLiveData()
}