package com.example.core.data.source.remote.response.moviesgenre

import com.google.gson.annotations.SerializedName

data class MoviesGenreResponse (

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("name")
    val name: String,

    )