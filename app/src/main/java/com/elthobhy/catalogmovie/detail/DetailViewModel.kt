package com.elthobhy.catalogmovie.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.elthobhy.catalogmovie.core.domain.model.DomainModel
import com.elthobhy.catalogmovie.core.domain.usecase.UseCase

class DetailViewModel(private val useCase: UseCase) : ViewModel() {
    fun getDetailById(id: Int): LiveData<DomainModel> = useCase.getDetailById(id)
    fun setFavoriteMovie(model: DomainModel) {
        val newState = !model.favorite
        useCase.setFavoriteMovie(model, newState)
    }
}