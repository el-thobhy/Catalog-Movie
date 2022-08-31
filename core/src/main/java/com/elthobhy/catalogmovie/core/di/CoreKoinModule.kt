package com.elthobhy.catalogmovie.core.di

import androidx.room.Room
import com.elthobhy.catalogmovie.core.data.Repository
import com.elthobhy.catalogmovie.core.data.local.LocalDataSource
import com.elthobhy.catalogmovie.core.data.local.room.Database
import com.elthobhy.catalogmovie.core.data.remote.RemoteDataSource
import com.elthobhy.catalogmovie.core.data.remote.networking.ApiConfig
import com.elthobhy.catalogmovie.core.domain.repository.RepositoryInterface
import com.elthobhy.catalogmovie.core.utils.AppExecutors
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val networking = module {
    single { ApiConfig }
}

val repository = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource() }
    factory { AppExecutors() }
    single<RepositoryInterface> { Repository(get(), get(), get()) }
}
val database = module {
    factory { get<Database>().dao() }
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("passphrase".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            Database::class.java, "movie_db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}