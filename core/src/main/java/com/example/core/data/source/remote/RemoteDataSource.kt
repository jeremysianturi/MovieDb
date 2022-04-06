package com.example.core.data.source.remote

import com.example.core.data.source.remote.network.ApiResponse
import com.example.core.data.source.remote.network.ApiService
import com.example.core.data.source.remote.response.banner.BannerResponse
import com.example.core.data.source.remote.response.comingsoon.ComingSoonResponse
import com.example.core.data.source.remote.response.detailmovie.DetailMovieResponse
import com.example.core.data.source.remote.response.moviesgenre.MoviesGenreResponse
import com.example.core.data.source.remote.response.moviesingenre.MoviesInGenreResponse
import com.example.core.data.source.remote.response.popularmovies.PopularMovieResponse
import com.example.core.data.source.remote.response.popularmoviesgrid.PopularMovieGridResponse
import com.example.core.data.source.remote.response.review.ReviewResponse
import com.example.core.data.source.remote.response.trailervideo.TrailerVideoResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(
    private val apiService: ApiService,
) {

    private val tag = RemoteDataSource::class.java.simpleName.toString()

    suspend fun getBanner(
        apiKey: String,
        language: String,
        sortBy: String,
        includeAdult: Boolean,
        includeVideo: Boolean,
        page: String
    ): Flow<ApiResponse<List<BannerResponse>>> {
        return flow {
            try {
                val response = apiService.getBanner(apiKey, language, sortBy, includeAdult, includeVideo, page)
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getPopularMovies(
        apiKey: String,
        language: String,
        sortBy: String,
        includeAdult: Boolean,
        includeVideo: Boolean,
        page: String
    ): Flow<ApiResponse<List<PopularMovieResponse>>> {
        return flow {
            try {
                val response = apiService.getPopularMovies(apiKey, language, sortBy, includeAdult, includeVideo, page)
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getComingSoon(
        apiKey: String,
        language: String,
        sortBy: String,
        includeAdult: Boolean,
        includeVideo: Boolean,
        page: String,
        year: String
    ): Flow<ApiResponse<List<ComingSoonResponse>>> {
        return flow {
            try {
                val response = apiService.getComingSoon(apiKey, language, sortBy, includeAdult, includeVideo, page,year)
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getDetailMovie(
        movieId: String,
        apiKey: String,
        language: String
    ): Flow<ApiResponse<DetailMovieResponse>> {
        return flow {
            try {
                val response = apiService.getDetailMovie(movieId,apiKey, language)
                val dataArray = response.data
                Timber.d("check data array di remote data source: $dataArray")
                if (dataArray.toString() != "null") {
                    emit(ApiResponse.Success(response.data))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getPopularMoviesGrid(
        apiKey: String,
        language: String,
        sortBy: String,
        includeAdult: Boolean,
        includeVideo: Boolean,
        page: String,
        year: String
    ): Flow<ApiResponse<List<PopularMovieGridResponse>>> {
        return flow {
            try {
                val response = apiService.getPopularMoviesGrid(apiKey, language, sortBy, includeAdult, includeVideo, page,year)
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getMoviesGenre(
        apiKey: String,
        language: String
    ): Flow<ApiResponse<List<MoviesGenreResponse>>> {
        return flow {
            try {
                val response = apiService.getMoviesGenre(apiKey, language)
                val dataArray = response.genre
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.genre))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getMoviesInGenre(
        apiKey: String,
        language: String,
        sortBy: String,
        includeAdult: Boolean,
        includeVideo: Boolean,
        page: String,
        withWatchMonetizatioTypes: String,
        withGenres: String
    ): Flow<ApiResponse<List<MoviesInGenreResponse>>> {
        return flow {
            try {
                val response = apiService.getMoviesInGenre(apiKey, language, sortBy, includeAdult, includeVideo, page, withWatchMonetizatioTypes, withGenres)
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getReview(
        movieId : String,
        apiKey: String,
        language: String,
        page: String
    ): Flow<ApiResponse<List<ReviewResponse>>> {
        return flow {
            try {
                val response = apiService.getReview(movieId, apiKey, language, page)
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }


    suspend fun getTrailerVideo(
        movieId : String,
        apiKey: String,
        language: String,
    ): Flow<ApiResponse<List<TrailerVideoResponse>>> {
        return flow {
            try {
                val response = apiService.getTrailerVideo(movieId, apiKey, language)
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

}