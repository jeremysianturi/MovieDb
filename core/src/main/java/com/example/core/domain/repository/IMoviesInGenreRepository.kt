package com.example.core.domain.repository

import com.example.core.data.Resource
import com.example.core.domain.model.MoviesInGenre
import com.example.core.domain.model.PopularMoviesGrid
import kotlinx.coroutines.flow.Flow

interface IMoviesInGenreRepository {

    fun getMoviesInGenre(apiKey: String, language: String, sortBy: String, includeAdult: Boolean, includeVideo: Boolean, page: String, withWatchMonetizationTypes: String, withGenres: String) : Flow<Resource<List<MoviesInGenre>>>

    fun getSearchMoviesInGenre(search: String): Flow<List<MoviesInGenre>>

}