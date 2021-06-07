package com.ghiyatshanif.moviecatalogue.core.di

import com.google.gson.Gson
import io.reactivex.disposables.CompositeDisposable
import org.koin.dsl.module

val utilityModule = module {

    single { Gson() }

    factory { CompositeDisposable() }
}