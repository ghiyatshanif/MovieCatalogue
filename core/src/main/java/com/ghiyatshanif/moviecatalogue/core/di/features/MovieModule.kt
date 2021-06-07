package com.ghiyatshanif.moviecatalogue.core.di.features

import com.ghiyatshanif.moviecatalogue.core.data.movie.MovieDataStore
import com.ghiyatshanif.moviecatalogue.core.data.movie.MovieRepository
import com.ghiyatshanif.moviecatalogue.core.data.movie.remote.MovieApi
import com.ghiyatshanif.moviecatalogue.core.data.movie.remote.MovieApiClient
import com.ghiyatshanif.moviecatalogue.core.di.BASE_URL
import com.ghiyatshanif.moviecatalogue.core.domain.movie.MovieInteractor
import com.ghiyatshanif.moviecatalogue.core.domain.movie.MovieUseCase
import com.ghiyatshanif.moviecatalogue.core.utils.network.ApiService
import org.koin.core.qualifier.named
import org.koin.dsl.module

val movieModule = module {

    single {
        ApiService.createReactiveService(
            MovieApiClient::class.java,
            get(),
            get(named(BASE_URL))
        )
    }

    single { MovieApi(get()) }

    single<MovieRepository> { MovieDataStore(get()) }

    single<MovieUseCase> { MovieInteractor(get()) }
}