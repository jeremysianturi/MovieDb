package com.example.core.domain.usecase.moviesgenre

import com.example.core.data.Resource
import com.example.core.domain.model.MoviesGenre
import kotlinx.coroutines.flow.Flow

interface MoviesGenreUsecase {

    fun getMoviesGenre(apiKey: String, language: String): Flow<Resource<List<MoviesGenre>>>

}