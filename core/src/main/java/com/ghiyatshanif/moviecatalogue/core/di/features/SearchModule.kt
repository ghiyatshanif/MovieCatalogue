package com.ghiyatshanif.moviecatalogue.core.di.features

import com.ghiyatshanif.moviecatalogue.core.data.search.SearchDataStore
import com.ghiyatshanif.moviecatalogue.core.data.search.SearchRepository
import com.ghiyatshanif.moviecatalogue.core.data.search.remote.SearchApi
import com.ghiyatshanif.moviecatalogue.core.data.search.remote.SearchApiClient
import com.ghiyatshanif.moviecatalogue.core.di.BASE_URL
import com.ghiyatshanif.moviecatalogue.core.domain.search.SearchInteractor
import com.ghiyatshanif.moviecatalogue.core.domain.search.SearchUseCase
import com.ghiyatshanif.moviecatalogue.core.utils.network.ApiService
import org.koin.core.qualifier.named
import org.koin.dsl.module

val searchModule = module {

    single {
        ApiService.createReactiveService(
            SearchApiClient::class.java,
            get(),
            get(named(BASE_URL))
        )
    }

    single { SearchApi(get()) }

    single<SearchRepository> { SearchDataStore(get()) }

    single<SearchUseCase> { SearchInteractor(get()) }
}