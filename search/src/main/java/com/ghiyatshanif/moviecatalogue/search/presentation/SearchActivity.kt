package com.ghiyatshanif.moviecatalogue.search.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ghiyatshanif.moviecatalogue.core.base.BaseActivity
import com.ghiyatshanif.moviecatalogue.core.domain.search.model.Search
import com.ghiyatshanif.moviecatalogue.core.domain.search.toMovie
import com.ghiyatshanif.moviecatalogue.core.domain.search.toTvShow
import com.ghiyatshanif.moviecatalogue.core.presentation.search.SearchAdapter
import com.ghiyatshanif.moviecatalogue.core.utils.AppConstants
import com.ghiyatshanif.moviecatalogue.core.utils.ext.showDefaultState
import com.ghiyatshanif.moviecatalogue.core.utils.ext.showEmptyState
import com.ghiyatshanif.moviecatalogue.core.utils.ext.showLoadingState
import com.ghiyatshanif.moviecatalogue.core.utils.network.Result
import com.ghiyatshanif.moviecatalogue.presentation.detail.MovieDetailActivity
import com.ghiyatshanif.moviecatalogue.presentation.detail.TvShowDetailActivity
import com.ghiyatshanif.moviecatalogue.search.R
import com.ghiyatshanif.moviecatalogue.search.di.searchViewModelModule
import com.google.android.material.textfield.TextInputLayout
import com.kennyc.view.MultiStateView
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

class SearchActivity : BaseActivity() {

    private val searchAdapter: SearchAdapter by lazy {
        SearchAdapter(::onItemSelected)
    }

    private lateinit var rvSearch: RecyclerView
    private lateinit var msvSearch: MultiStateView

    private val searchViewModel: SearchViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        loadKoinModules(searchViewModelModule)

        val tilSearch: TextInputLayout = findViewById(R.id.tilSearch)
        val edtSearch: EditText = findViewById(R.id.edtSearch)
        rvSearch = findViewById(R.id.rvSearch)
        msvSearch = findViewById(R.id.msvSearch)

        setupToolbar(title = getString(com.ghiyatshanif.moviecatalogue.R.string.label_search_movie), isChild = true)

        rvSearch.apply {
            layoutManager = LinearLayoutManager(this@SearchActivity)
            adapter = searchAdapter
        }

        initObserver()

        tilSearch.setEndIconOnClickListener {
            val query = edtSearch.text.toString().trim()
            if (query.isNotEmpty()) {
                actionSearch(query)
            }
        }
    }

    private fun actionSearch(query: String) {
        msvSearch.visibility = View.VISIBLE
        searchViewModel.searchAll(query)
    }

    private fun initObserver() {
        searchViewModel.search.observe(this, {
            when (it) {
                is Result.Loading -> {
                    msvSearch.showLoadingState()
                }
                is Result.Failure -> {
                    msvSearch.showEmptyState(emptyMessage = it.message)
                }
                is Result.Empty -> {
                    msvSearch.showEmptyState(
                        title = getString(com.ghiyatshanif.moviecatalogue.R.string.title_empty),
                        emptyMessage = getString(com.ghiyatshanif.moviecatalogue.R.string.message_result_not_found)
                    )
                }
                is Result.Success -> {
                    msvSearch.showDefaultState()
                    setData(it.data)
                }
                else -> {
                }
            }
        })
    }

    private fun setData(data: List<Search>) {
        searchAdapter.setData(data)
    }

    private fun onItemSelected(search: Search) {
        Log.d(AppConstants.MOVIE_TAG, search.mediaType)
        if (search.mediaType == AppConstants.MOVIE) {
            MovieDetailActivity.start(this, search.toMovie())
        } else if (search.mediaType == AppConstants.TV) {
            TvShowDetailActivity.start(this, search.toTvShow())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unloadKoinModules(searchViewModelModule)
    }
}