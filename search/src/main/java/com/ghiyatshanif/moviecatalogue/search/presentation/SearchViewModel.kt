package com.ghiyatshanif.moviecatalogue.search.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ghiyatshanif.moviecatalogue.core.domain.search.SearchUseCase
import com.ghiyatshanif.moviecatalogue.core.domain.search.model.Search
import com.ghiyatshanif.moviecatalogue.core.utils.AppConstants
import com.ghiyatshanif.moviecatalogue.core.utils.network.Result
import com.ghiyatshanif.moviecatalogue.core.utils.network.genericErrorHandler
import com.ghiyatshanif.moviecatalogue.core.utils.rx.addTo
import com.ghiyatshanif.moviecatalogue.core.utils.rx.singleScheduler
import io.reactivex.disposables.CompositeDisposable

class SearchViewModel(private val useCase: SearchUseCase, private val disposable: CompositeDisposable) :
    ViewModel() {

    val search: LiveData<Result<List<Search>>> by lazy { _search }

    private val _search = MutableLiveData<Result<List<Search>>>()

    init {
        _search.value = Result.default()
    }

    fun searchAll(query: String) {
        _search.value = Result.loading()

        useCase.searchAll(apiKey = AppConstants.API_KEY, language = AppConstants.LANG, query = query)
            .compose(singleScheduler())
            .subscribe({
                if (it.isEmpty()) {
                    _search.value = Result.empty()
                } else {
                    _search.value = Result.success(it)
                }
            }, { genericErrorHandler(it, _search) })
            .addTo(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        if (!disposable.isDisposed) {
            disposable.dispose()
        }
    }
}