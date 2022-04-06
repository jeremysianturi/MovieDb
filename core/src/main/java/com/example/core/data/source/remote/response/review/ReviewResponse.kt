package com.example.core.data.source.remote.response.review

import com.google.gson.annotations.SerializedName

data class ReviewResponse (

    @field:SerializedName("author")
    val author: String,

    @field:SerializedName("author_details")
    val authorDetails: AuthorDetails,

    @field:SerializedName("content")
    val content: String,

    @field:SerializedName("created_at")
    val createdAt: String,

    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("updated_at")
    val updatedAt: String,

    @field:SerializedName("url")
    val url: String,


    )