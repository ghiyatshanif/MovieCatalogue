package com.ghiyatshanif.moviecatalogue.di

import com.ghiyatshanif.moviecatalogue.presentation.movie.MovieViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel {
        MovieViewModel(
            get(),
            get()
        )
    }
}