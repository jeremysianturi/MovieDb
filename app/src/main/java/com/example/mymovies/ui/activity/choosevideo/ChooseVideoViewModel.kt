package com.example.mymovies.ui.activity.choosevideo

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.domain.usecase.trailervideo.TrailerVideoUsecase
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class ChooseVideoViewModel @ViewModelInject constructor(
    private val trailerVideoUsecase: TrailerVideoUsecase,
) : ViewModel() {

    fun getTrailerVideo(movieId: String, apiKey: String, language: String) =
        trailerVideoUsecase.getTrailerVideo(movieId, apiKey, language).asLiveData()

}