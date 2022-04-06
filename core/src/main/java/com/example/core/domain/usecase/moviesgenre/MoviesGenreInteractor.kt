package com.example.core.domain.usecase.moviesgenre

import com.example.core.data.Resource
import com.example.core.data.repository.MoviesGenreRepository
import com.example.core.domain.model.MoviesGenre
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoviesGenreInteractor @Inject constructor(private val moviesGenreRepository: MoviesGenreRepository) :
    MoviesGenreUsecase {

    override fun getMoviesGenre(apiKey: String, language: String): Flow<Resource<List<MoviesGenre>>> =
        moviesGenreRepository.getMoviesGenre(apiKey, language)

}