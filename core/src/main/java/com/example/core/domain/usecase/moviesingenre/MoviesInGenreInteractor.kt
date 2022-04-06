package com.example.core.domain.usecase.moviesingenre

import android.graphics.Movie
import com.example.core.data.Resource
import com.example.core.data.repository.MoviesInGenreRepository
import com.example.core.domain.model.MoviesInGenre
import com.example.core.domain.model.PopularMoviesGrid
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoviesInGenreInteractor @Inject constructor(private val moviesInGenreRepository: MoviesInGenreRepository) :
    MoviesInGenreUsecase {

    override fun getMoviesInGenre(apiKey: String, language: String, sortBy: String, includeAdult: Boolean, includeVideo: Boolean, page: String, withWatchMonetizationTypes: String, withGenres: String): Flow<Resource<List<MoviesInGenre>>> =
        moviesInGenreRepository.getMoviesInGenre(apiKey, language, sortBy, includeAdult, includeVideo, page,withWatchMonetizationTypes, withGenres)

    override fun getSearchMoviesInGenre(search: String): Flow<List<MoviesInGenre>> =
        moviesInGenreRepository.getSearchMoviesInGenre(search)

}