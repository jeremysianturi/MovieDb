package com.example.mymovies.ui.fragment.moviesgenre

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.domain.usecase.moviesgenre.MoviesGenreUsecase
import com.example.core.domain.usecase.moviesingenre.MoviesInGenreUsecase
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class MoviesGenreViewModel @ViewModelInject constructor(
    private val moviesGenreUsecase: MoviesGenreUsecase,
) : ViewModel() {

    fun getMoviesGenre(apiKey: String, language: String) =
        moviesGenreUsecase.getMoviesGenre(apiKey, language).asLiveData()

}