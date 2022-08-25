package com.elthobhy.catalogmovie.di

import com.elthobhy.catalogmovie.core.domain.usecase.RepositoryInteract
import com.elthobhy.catalogmovie.core.domain.usecase.UseCase
import com.elthobhy.catalogmovie.movie.MovieViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCase = module {
    factory<UseCase> { RepositoryInteract(get()) }
}

@ExperimentalCoroutinesApi
@FlowPreview
val viewModel = module {
    viewModel { MovieViewModel(get()) }
}