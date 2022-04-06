package com.example.core.domain.usecase.review

import com.example.core.data.Resource
import com.example.core.data.repository.ReviewRepository
import com.example.core.domain.model.Review
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReviewInteractor @Inject constructor(private val reviewRepository: ReviewRepository) :
    ReviewUsecase {

    override fun getReview(movieId: String, apiKey: String, language: String, page: String): Flow<Resource<List<Review>>> =
        reviewRepository.getReview(movieId, apiKey, language, page)

}