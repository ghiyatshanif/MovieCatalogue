package com.ghiyatshanif.moviecatalogue.core.domain.movie

import com.ghiyatshanif.moviecatalogue.core.domain.movie.model.response.Movie
import com.ghiyatshanif.moviecatalogue.core.domain.movie.model.response.TvShow
import io.reactivex.Single

interface MovieUseCase {

    fun getMovies(apiKey: String, language: String): Single<List<Movie>>
    fun getTvShows(apiKey: String, language: String): Single<List<TvShow>>
}