package com.example.core.data.source.remote.response.moviesingenre

import com.example.core.data.source.remote.response.banner.BannerResponse
import com.google.gson.annotations.SerializedName

data class ListMoviesInGenreResponse(

    @field:SerializedName("page")
    val page: Int,

    @field:SerializedName("results")
    val results: List<MoviesInGenreResponse>,

    @field:SerializedName("total_pages")
    val totalPages: Int,

    @field:SerializedName("total_results")
    val totalResults: Int,
)