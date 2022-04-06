package com.example.core.helper.helpermapper

import com.example.core.data.source.local.entity.MoviesGenreEntity
import com.example.core.data.source.remote.response.moviesgenre.MoviesGenreResponse
import com.example.core.domain.model.MoviesGenre

object DataMapperMoviesGenre {

    fun mapResponsetoEntities(input: List<MoviesGenreResponse>): List<MoviesGenreEntity> {
        val moviesGenreList = ArrayList<MoviesGenreEntity>()
        input.map {
            val moviesGenre = MoviesGenreEntity(
                id = it.id,
                name = it.name
            )
            moviesGenreList.add(moviesGenre)
        }

        return moviesGenreList
    }

    fun mapEntitiestoDomain(input: List<MoviesGenreEntity>): List<MoviesGenre> =
        input.map {
            MoviesGenre(
                id = it.id,
                name = it.name
            )
        }
}