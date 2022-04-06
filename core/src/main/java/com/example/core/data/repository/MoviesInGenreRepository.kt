package com.example.core.data.repository

import com.example.core.data.NetworkBoundResourceWithDeleteLocalData
import com.example.core.data.Resource
import com.example.core.data.source.local.room.LocalDataSource
import com.example.core.data.source.remote.RemoteDataSource
import com.example.core.data.source.remote.network.ApiResponse
import com.example.core.data.source.remote.response.moviesingenre.MoviesInGenreResponse
import com.example.core.domain.model.MoviesInGenre
import com.example.core.domain.model.PopularMoviesGrid
import com.example.core.domain.repository.IMoviesInGenreRepository
import com.example.core.helper.helpermapper.DataMapperMoviesInGenre
import com.example.core.helper.helpermapper.DataMapperPopularMoviesGrid
import com.example.core.utils.AppExecutors
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesInGenreRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IMoviesInGenreRepository {

    override fun getMoviesInGenre(apiKey: String, language: String, sortBy: String, includeAdult: Boolean, includeVideo: Boolean, page: String, withWatchMonetizationTypes: String, withGenres: String): Flow<Resource<List<MoviesInGenre>>> =
        object :
            NetworkBoundResourceWithDeleteLocalData<List<MoviesInGenre>, List<MoviesInGenreResponse>>() {

            override fun loadFromDB(): Flow<List<MoviesInGenre>> {
                return localDataSource.getMoviesInGenre().map {
                    DataMapperMoviesInGenre.mapEntitiestoDomain(it)
                }
            }

            override fun shouldFetch(data: List<MoviesInGenre>?): Boolean =
                true

            override suspend fun createCall(): Flow<ApiResponse<List<MoviesInGenreResponse>>> =
                remoteDataSource.getMoviesInGenre(apiKey, language, sortBy, includeAdult, includeVideo, page, withWatchMonetizationTypes, withGenres)

            override suspend fun saveCallResult(data: List<MoviesInGenreResponse>) {
                val list = DataMapperMoviesInGenre.mapResponsetoEntities(data)
                localDataSource.insertMoviesInGenre(list)
            }

            override suspend fun emptyDataBase() {
                localDataSource.deleteMoviesInGenre()
            }

        }.asFlow()

    override fun getSearchMoviesInGenre(search: String): Flow<List<MoviesInGenre>> {
        return localDataSource.getSearchMoviesInGenre(search).map {
            DataMapperMoviesInGenre.mapEntitiestoDomain(it)
        }
    }
}