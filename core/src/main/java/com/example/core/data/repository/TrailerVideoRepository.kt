package com.example.core.data.repository

import com.example.core.data.NetworkBoundResourceWithDeleteLocalData
import com.example.core.data.Resource
import com.example.core.data.source.local.room.LocalDataSource
import com.example.core.data.source.remote.RemoteDataSource
import com.example.core.data.source.remote.network.ApiResponse
import com.example.core.data.source.remote.response.trailervideo.TrailerVideoResponse
import com.example.core.domain.model.TrailerVideo
import com.example.core.domain.repository.ITrailerVideoRepository
import com.example.core.helper.helpermapper.DataMapperTrailerVideo
import com.example.core.utils.AppExecutors
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TrailerVideoRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : ITrailerVideoRepository {

    override fun getTrailerVideo(movieId: String, apiKey: String, language: String): Flow<Resource<List<TrailerVideo>>> =
        object :
            NetworkBoundResourceWithDeleteLocalData<List<TrailerVideo>, List<TrailerVideoResponse>>() {

            override fun loadFromDB(): Flow<List<TrailerVideo>> {
                return localDataSource.getTrailerVideo().map {
                    DataMapperTrailerVideo.mapEntitiestoDomain(it)
                }
            }

            override fun shouldFetch(data: List<TrailerVideo>?): Boolean =
                true

            override suspend fun createCall(): Flow<ApiResponse<List<TrailerVideoResponse>>> =
                remoteDataSource.getTrailerVideo(movieId, apiKey, language)

            override suspend fun saveCallResult(data: List<TrailerVideoResponse>) {
                val list = DataMapperTrailerVideo.mapResponsetoEntities(data)
                localDataSource.insertTrailerVideo(list)
            }

            override suspend fun emptyDataBase() {
                localDataSource.deleteTrailerVideo()
            }

        }.asFlow()
}