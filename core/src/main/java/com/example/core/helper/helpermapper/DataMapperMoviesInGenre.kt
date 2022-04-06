package com.example.core.helper.helpermapper

import com.example.core.data.source.local.entity.BannerEntity
import com.example.core.data.source.local.entity.MoviesInGenreEntity
import com.example.core.data.source.remote.response.banner.BannerResponse
import com.example.core.data.source.remote.response.moviesingenre.MoviesInGenreResponse
import com.example.core.domain.model.Banner
import com.example.core.domain.model.MoviesInGenre

object DataMapperMoviesInGenre {

    fun mapResponsetoEntities(input: List<MoviesInGenreResponse>): List<MoviesInGenreEntity> {
        val moviesInGenreList = ArrayList<MoviesInGenreEntity>()
        input.map {
            val moviesInGenre = MoviesInGenreEntity(
                adult = it.adult,
                backdropPath = it.backdropPath,
                genreIds = it.genreIds.toString(),
                id = it.id,
                originalLanguage = it.originalLanguage,
                originalTitle = it.originalTitle,
                overview = it.overview,
                popularity = it.popularity,
                posterPath = it.posterPath,
                releaseDate = it.releaseDate,
                title = it.title,
                video = it.video,
                voteAverage = it.voteAverage,
                voteCount = it.voteCount
            )
            moviesInGenreList.add(moviesInGenre)
        }

        return moviesInGenreList
    }

    fun mapEntitiestoDomain(input: List<MoviesInGenreEntity>): List<MoviesInGenre> =
        input.map {
            MoviesInGenre(
                adult = it.adult,
                backdropPath = it.backdropPath,
                genreIds = it.genreIds,
                id = it.id,
                originalLanguage = it.originalLanguage,
                originalTitle = it.originalTitle,
                overview = it.overview,
                popularity = it.popularity,
                posterPath = it.posterPath,
                releaseDate = it.releaseDate,
                title = it.title,
                video = it.video,
                voteAverage = it.voteAverage,
                voteCount = it.voteCount
            )
        }
}