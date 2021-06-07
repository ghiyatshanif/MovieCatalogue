package com.ghiyatshanif.moviecatalogue.core.data.movie

import com.ghiyatshanif.moviecatalogue.core.data.movie.model.response.MovieItem
import com.ghiyatshanif.moviecatalogue.core.data.movie.model.response.TvShowItem
import com.ghiyatshanif.moviecatalogue.core.data.movie.remote.MovieApi
import com.ghiyatshanif.moviecatalogue.core.utils.network.singleApiError
import io.reactivex.Single

class MovieDataStore(api: MovieApi) :
    MovieRepository {

    override val webService = api

    override fun getMovies(apiKey: String, language: String): Single<List<MovieItem>> {
        return webService.getMovies(apiKey, language).lift(singleApiError()).map { it.results }
    }

    override fun getTvShows(apiKey: String, language: String): Single<List<TvShowItem>> {
        return webService.getTvShows(apiKey, language).lift(singleApiError()).map { it.results }
    }
}