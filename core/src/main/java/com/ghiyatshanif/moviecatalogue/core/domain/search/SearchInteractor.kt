package com.ghiyatshanif.moviecatalogue.core.domain.search

import com.ghiyatshanif.moviecatalogue.core.data.search.SearchRepository
import com.ghiyatshanif.moviecatalogue.core.domain.search.model.Search
import io.reactivex.Single

class SearchInteractor(private val repository: SearchRepository) : SearchUseCase {

    override fun searchAll(apiKey: String, language: String, query: String): Single<List<Search>> {
        return repository.searchAll(apiKey, language, query).map {
            it.map { searchItem ->
                searchItem.toDomain()
            }
        }
    }
}