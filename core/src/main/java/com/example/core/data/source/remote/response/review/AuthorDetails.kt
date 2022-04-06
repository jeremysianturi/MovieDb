package com.example.core.data.source.remote.response.review

import com.google.gson.annotations.SerializedName

data class AuthorDetails (

    @field:SerializedName("name")
    val name: String?,

    @field:SerializedName("username")
    val username: String?,

    @field:SerializedName("avatar_path")
    val avatarPath: String?,

    @field:SerializedName("rating")
    val rating: Int?

    )