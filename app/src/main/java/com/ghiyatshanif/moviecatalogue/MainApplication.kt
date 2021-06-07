package com.ghiyatshanif.moviecatalogue

import com.ghiyatshanif.moviecatalogue.core.base.BaseApplication
import com.ghiyatshanif.moviecatalogue.core.di.apiModule
import com.ghiyatshanif.moviecatalogue.core.di.dbModule
import com.ghiyatshanif.moviecatalogue.core.di.features.favoriteModule
import com.ghiyatshanif.moviecatalogue.core.di.features.movieModule
import com.ghiyatshanif.moviecatalogue.core.di.features.searchModule
import com.ghiyatshanif.moviecatalogue.core.di.utilityModule
import com.ghiyatshanif.moviecatalogue.di.appModule
import org.koin.core.module.Module

class MainApplication : BaseApplication() {

    override fun getDefinedModules(): List<Module> {
        return listOf(
            utilityModule,
            apiModule,
            dbModule,
            movieModule,
            favoriteModule,
            searchModule,
            appModule
        )
    }
}