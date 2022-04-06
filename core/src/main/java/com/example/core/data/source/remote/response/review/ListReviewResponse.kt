package com.example.core.data.source.remote.response.review

import com.example.core.data.source.remote.response.moviesingenre.MoviesInGenreResponse
import com.google.gson.annotations.SerializedName

data class ListReviewResponse(

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("page")
    val page: Int,

    @field:SerializedName("results")
    val results: List<ReviewResponse>,

    @field:SerializedName("total_pages")
    val totalPages: Int,

    @field:SerializedName("total_results")
    val totalResults: Int,
)