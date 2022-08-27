package com.elthobhy.catalogmovie.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.elthobhy.catalogmovie.core.domain.usecase.UseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*

@FlowPreview
@ExperimentalCoroutinesApi
class SearchViewModel(private val useCase: UseCase) : ViewModel() {
    val queryChannel = MutableStateFlow("")

    val movieResult = queryChannel
        .debounce(300)
        .distinctUntilChanged()
        .filter {
            it.trim().isNotEmpty()
        }
        .flatMapLatest {
            useCase.getSearchMovies(it)
        }.asLiveData()

    val tvShowResult = queryChannel
        .debounce(300)
        .distinctUntilChanged()
        .filter {
            it.trim().isNotEmpty()
        }
        .flatMapLatest {
            useCase.getSearchTvShows(it)
        }.asLiveData()
    val movieFavoriteResult = queryChannel
        .debounce(300)
        .distinctUntilChanged()
        .filter {
            it.trim().isNotEmpty()
        }
        .flatMapLatest {
            useCase.getSearchFavoriteMovies(it)
        }.asLiveData()

    val tvShowFavoriteResult = queryChannel
        .debounce(300)
        .distinctUntilChanged()
        .filter {
            it.trim().isNotEmpty()
        }
        .flatMapLatest {
            useCase.getSearchFavoriteTvShows(it)
        }.asLiveData()
}