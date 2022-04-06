package com.example.core.domain.repository

import com.example.core.data.Resource
import com.example.core.domain.model.TrailerVideo
import kotlinx.coroutines.flow.Flow

interface ITrailerVideoRepository {

    fun getTrailerVideo(movieId: String, apiKey: String, language: String) : Flow<Resource<List<TrailerVideo>>>

}