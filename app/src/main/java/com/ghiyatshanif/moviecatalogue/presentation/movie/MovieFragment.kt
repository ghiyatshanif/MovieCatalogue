package com.ghiyatshanif.moviecatalogue.presentation.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ghiyatshanif.moviecatalogue.R
import com.ghiyatshanif.moviecatalogue.core.domain.movie.model.response.Movie
import com.ghiyatshanif.moviecatalogue.core.presentation.movie.MovieAdapter
import com.ghiyatshanif.moviecatalogue.core.utils.ext.showDefaultState
import com.ghiyatshanif.moviecatalogue.core.utils.ext.showErrorState
import com.ghiyatshanif.moviecatalogue.core.utils.ext.showLoadingState
import com.ghiyatshanif.moviecatalogue.core.utils.network.Result
import com.ghiyatshanif.moviecatalogue.presentation.detail.MovieDetailActivity
import com.kennyc.view.MultiStateView
import org.koin.android.viewmodel.ext.android.viewModel

class MovieFragment : Fragment() {

    private val movieAdapter: MovieAdapter by lazy {
        MovieAdapter(::onMovieSelected)
    }

    private lateinit var rvMovie: RecyclerView
    private lateinit var msvMovie: MultiStateView

    private val movieViewModel: MovieViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvMovie = view.findViewById(R.id.rvMovie)
        msvMovie = view.findViewById(R.id.msvMovie)

        initObserver()

        rvMovie.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = movieAdapter
        }

        getMovies()
    }

    private fun getMovies() {
        movieViewModel.getMovies()
    }

    private fun initObserver() {
        movieViewModel.movies.observe(viewLifecycleOwner, {
            when (it) {
                is Result.Loading -> {
                    msvMovie.showLoadingState()
                }
                is Result.Failure -> {
                    msvMovie.showErrorState(errorMessage = it.message.toString())
                }
                is Result.Success -> {
                    msvMovie.showDefaultState()
                    setMovieData(it.data)
                }
                else -> {
                }
            }
        })
    }

    private fun onMovieSelected(movie: Movie) {
        MovieDetailActivity.start(requireContext(), movie)
    }

    private fun setMovieData(data: List<Movie>) {
        movieAdapter.setMovies(data)
    }
}
