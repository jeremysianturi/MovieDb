package com.example.core.domain.repository

import com.example.core.data.Resource
import com.example.core.domain.model.Review
import kotlinx.coroutines.flow.Flow

interface IReviewRepository {

    fun getReview(movieId: String, apiKey: String, language: String, page: String) : Flow<Resource<List<Review>>>

}