package com.ghiyatshanif.moviecatalogue.core.data.favorite

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ghiyatshanif.moviecatalogue.core.data.favorite.local.MoviesDao
import com.ghiyatshanif.moviecatalogue.core.data.favorite.local.TvShowsDao
import com.ghiyatshanif.moviecatalogue.core.data.favorite.model.response.MovieEntity
import com.ghiyatshanif.moviecatalogue.core.data.favorite.model.response.TvShowEntity

@Database(
    entities = [
        MovieEntity::class,
        TvShowEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun moviesDao(): MoviesDao
    abstract fun tvShowsDao(): TvShowsDao
}