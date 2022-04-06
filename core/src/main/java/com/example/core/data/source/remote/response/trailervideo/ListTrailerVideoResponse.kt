package com.example.core.data.source.remote.response.trailervideo

import com.google.gson.annotations.SerializedName

data class ListTrailerVideoResponse(

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("results")
    val results: List<TrailerVideoResponse>
)