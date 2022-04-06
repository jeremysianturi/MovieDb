package com.example.core.domain.repository

import com.example.core.data.Resource
import com.example.core.domain.model.MoviesGenre
import kotlinx.coroutines.flow.Flow

interface IMoviesGenreRepository {

    fun getMoviesGenre(apiKey: String, language: String) : Flow<Resource<List<MoviesGenre>>>

}