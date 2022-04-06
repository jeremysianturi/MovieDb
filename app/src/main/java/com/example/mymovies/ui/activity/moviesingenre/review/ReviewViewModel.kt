package com.example.mymovies.ui.activity.moviesingenre.review

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.domain.usecase.review.ReviewUsecase
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class ReviewViewModel @ViewModelInject constructor(
    private val reviewUsecase: ReviewUsecase,
) : ViewModel() {

    fun getReview(movieId: String, apiKey: String, language: String, page: String) =
        reviewUsecase.getReview(movieId, apiKey, language,page).asLiveData()

}