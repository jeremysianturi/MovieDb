package com.example.core.domain.usecase.review

import com.example.core.data.Resource
import com.example.core.domain.model.PopularMoviesGrid
import com.example.core.domain.model.Review
import kotlinx.coroutines.flow.Flow

interface ReviewUsecase {

    fun getReview(movieId: String, apiKey: String, language: String, page: String): Flow<Resource<List<Review>>>

}