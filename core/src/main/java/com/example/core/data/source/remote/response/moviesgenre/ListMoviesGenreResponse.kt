package com.example.core.data.source.remote.response.moviesgenre

import com.example.core.data.source.remote.response.banner.BannerResponse
import com.google.gson.annotations.SerializedName

data class ListMoviesGenreResponse(

    @field:SerializedName("genres")
    val genre: List<MoviesGenreResponse>

)