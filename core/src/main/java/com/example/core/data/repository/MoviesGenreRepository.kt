package com.example.core.data.repository

import com.example.core.data.NetworkBoundResourceWithDeleteLocalData
import com.example.core.data.Resource
import com.example.core.data.source.local.room.LocalDataSource
import com.example.core.data.source.remote.RemoteDataSource
import com.example.core.data.source.remote.network.ApiResponse
import com.example.core.data.source.remote.response.moviesgenre.MoviesGenreResponse
import com.example.core.domain.model.MoviesGenre
import com.example.core.domain.repository.IMoviesGenreRepository
import com.example.core.helper.helpermapper.DataMapperMoviesGenre
import com.example.core.utils.AppExecutors
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesGenreRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IMoviesGenreRepository {

    override fun getMoviesGenre(apiKey: String, language: String): Flow<Resource<List<MoviesGenre>>> =
        object :
            NetworkBoundResourceWithDeleteLocalData<List<MoviesGenre>, List<MoviesGenreResponse>>() {

            override fun loadFromDB(): Flow<List<MoviesGenre>> {
                return localDataSource.getMoviesGenre().map {
                    DataMapperMoviesGenre.mapEntitiestoDomain(it)
                }
            }

            override fun shouldFetch(data: List<MoviesGenre>?): Boolean =
                true

            override suspend fun createCall(): Flow<ApiResponse<List<MoviesGenreResponse>>> =
                remoteDataSource.getMoviesGenre(apiKey, language)

            override suspend fun saveCallResult(data: List<MoviesGenreResponse>) {
                val list = DataMapperMoviesGenre.mapResponsetoEntities(data)
                localDataSource.insertMoviesGenre(list)
            }

            override suspend fun emptyDataBase() {
                localDataSource.deleteMoviesGenre()
            }

        }.asFlow()
}