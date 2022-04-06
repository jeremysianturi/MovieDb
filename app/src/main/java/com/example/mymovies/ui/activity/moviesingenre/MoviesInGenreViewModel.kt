package com.example.mymovies.ui.activity.moviesingenre

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.domain.usecase.moviesingenre.MoviesInGenreUsecase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest

@ExperimentalCoroutinesApi
class MoviesInGenreViewModel @ViewModelInject constructor(
    private val moviesInGenreUsecase: MoviesInGenreUsecase,
) : ViewModel() {

    val searchQuery = MutableStateFlow("")

    private val moviesInGenreFlow = searchQuery.flatMapLatest {
        moviesInGenreUsecase.getSearchMoviesInGenre(it)
    }

    val search = moviesInGenreFlow.asLiveData()

    fun getMoviesInGenre(apiKey: String, language: String, sortBy: String, includeAdult: Boolean, includeVideo: Boolean, page: String, withWatchMonetizationTypes: String, withGenres: String) =
        moviesInGenreUsecase.getMoviesInGenre(apiKey, language, sortBy, includeAdult, includeVideo, page, withWatchMonetizationTypes, withGenres).asLiveData()

}