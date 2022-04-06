package com.example.core.domain.usecase.trailervideo

import com.example.core.data.Resource
import com.example.core.data.repository.TrailerVideoRepository
import com.example.core.domain.model.TrailerVideo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TrailerVideoInteractor @Inject constructor(private val trailerVideoRepository: TrailerVideoRepository) :
    TrailerVideoUsecase {

    override fun getTrailerVideo(movieId: String, apiKey: String, language: String): Flow<Resource<List<TrailerVideo>>> =
        trailerVideoRepository.getTrailerVideo(movieId, apiKey, language)

}