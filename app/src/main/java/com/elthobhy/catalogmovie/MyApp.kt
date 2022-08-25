package com.elthobhy.catalogmovie

import android.app.Application
import com.elthobhy.catalogmovie.core.di.database
import com.elthobhy.catalogmovie.core.di.networking
import com.elthobhy.catalogmovie.core.di.repository
import com.elthobhy.catalogmovie.di.useCase
import com.elthobhy.catalogmovie.di.viewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

@FlowPreview
@ExperimentalCoroutinesApi
class MyApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger(Level.NONE)
            androidContext(this@MyApp)
            modules(
                database,
                networking,
                repository,
                viewModel,
                useCase
            )
        }
    }
}