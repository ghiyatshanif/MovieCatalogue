package com.ghiyatshanif.moviecatalogue.core.di.features

import com.ghiyatshanif.moviecatalogue.core.data.favorite.FavoriteDataStore
import com.ghiyatshanif.moviecatalogue.core.data.favorite.FavoriteRepository
import com.ghiyatshanif.moviecatalogue.core.domain.favorite.FavoriteInteractor
import com.ghiyatshanif.moviecatalogue.core.domain.favorite.FavoriteUseCase
import com.ghiyatshanif.moviecatalogue.core.presentation.favorite.FavoriteViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {

    single<FavoriteRepository> { FavoriteDataStore(get(), get()) }

    single<FavoriteUseCase> { FavoriteInteractor(get()) }

    viewModel {
        FavoriteViewModel(
            get(),
            get()
        )
    }

}