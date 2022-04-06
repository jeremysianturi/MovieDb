package com.example.core.data.repository

import com.example.core.data.NetworkBoundResourceWithDeleteLocalData
import com.example.core.data.Resource
import com.example.core.data.source.local.room.LocalDataSource
import com.example.core.data.source.remote.RemoteDataSource
import com.example.core.data.source.remote.network.ApiResponse
import com.example.core.data.source.remote.response.review.ReviewResponse
import com.example.core.domain.model.Review
import com.example.core.domain.repository.IReviewRepository
import com.example.core.helper.helpermapper.DataMapperReview
import com.example.core.utils.AppExecutors
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ReviewRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IReviewRepository {

    override fun getReview(movieId: String, apiKey: String, language: String, page: String): Flow<Resource<List<Review>>> =
        object :
            NetworkBoundResourceWithDeleteLocalData<List<Review>, List<ReviewResponse>>() {

            override fun loadFromDB(): Flow<List<Review>> {
                return localDataSource.getReview().map {
                    DataMapperReview.mapEntitiestoDomain(it)
                }
            }

            override fun shouldFetch(data: List<Review>?): Boolean =
                true

            override suspend fun createCall(): Flow<ApiResponse<List<ReviewResponse>>> =
                remoteDataSource.getReview(movieId, apiKey, language, page)

            override suspend fun saveCallResult(data: List<ReviewResponse>) {
                val list = DataMapperReview.mapResponsetoEntities(data)
                localDataSource.insertReview(list)
            }

            override suspend fun emptyDataBase() {
                localDataSource.deleteReview()
            }

        }.asFlow()

}