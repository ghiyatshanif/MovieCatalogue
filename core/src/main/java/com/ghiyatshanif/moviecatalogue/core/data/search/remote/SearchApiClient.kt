package com.ghiyatshanif.moviecatalogue.core.data.search.remote

import com.ghiyatshanif.moviecatalogue.core.data.search.model.SearchResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApiClient {
    @GET("search/multi")
    fun searchAll(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("query") query: String
    ) : Single<SearchResponse>
}