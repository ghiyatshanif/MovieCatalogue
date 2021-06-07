package com.ghiyatshanif.moviecatalogue.core.data.search

import com.ghiyatshanif.moviecatalogue.core.data.search.model.SearchItem
import com.ghiyatshanif.moviecatalogue.core.data.search.remote.SearchApi
import io.reactivex.Single

class SearchDataStore(api: SearchApi) : SearchRepository {

    override val webService = api

    override fun searchAll(apiKey: String, language: String, query: String): Single<List<SearchItem>> {
        return webService.searchAll(apiKey, language, query).map { it.results }
    }
}