package com.elthobhy.catalogmovie.detail

import androidx.lifecycle.ViewModel
import com.elthobhy.catalogmovie.core.domain.model.DomainModel
import com.elthobhy.catalogmovie.core.domain.usecase.UseCase

class DetailViewModel(private val useCase: UseCase) : ViewModel() {
    fun setFavoriteMovie(model: DomainModel, state: Boolean) =
        useCase.setFavoriteMovie(model, state)
}