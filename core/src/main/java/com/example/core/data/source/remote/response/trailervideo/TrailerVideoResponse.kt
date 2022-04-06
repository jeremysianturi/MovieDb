package com.example.core.data.source.remote.response.trailervideo

import com.google.gson.annotations.SerializedName

data class TrailerVideoResponse (

    @field:SerializedName("iso_639_1")
    val iso_639_1: String,

    @field:SerializedName("iso_3166_1")
    val iso_3166_1: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("key")
    val key: String,

    @field:SerializedName("site")
    val site: String,

    @field:SerializedName("size")
    val size: Int,

    @field:SerializedName("type")
    val type: String,

    @field:SerializedName("official")
    val official: Boolean,

    @field:SerializedName("published_at")
    val publishedAt: String,

    @field:SerializedName("id")
    val id: String

    )