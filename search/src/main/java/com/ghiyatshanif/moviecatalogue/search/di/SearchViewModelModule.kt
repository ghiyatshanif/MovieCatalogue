package com.ghiyatshanif.moviecatalogue.search.di

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val searchViewModelModule = module {

    viewModel {
        com.ghiyatshanif.moviecatalogue.search.presentation.SearchViewModel(
            get(),
            get()
        )
    }
}