package com.example.core.domain.usecase.moviesingenre

import com.example.core.data.Resource
import com.example.core.domain.model.MoviesGenre
import com.example.core.domain.model.MoviesInGenre
import com.example.core.domain.model.PopularMoviesGrid
import kotlinx.coroutines.flow.Flow

interface MoviesInGenreUsecase {

    fun getMoviesInGenre(apiKey: String, language: String, sortBy: String, includeAdult: Boolean, includeVideo: Boolean, page: String, withWatchMonetizationTypes: String, withGenres: String): Flow<Resource<List<MoviesInGenre>>>

    fun getSearchMoviesInGenre(search: String): Flow<List<MoviesInGenre>>

}