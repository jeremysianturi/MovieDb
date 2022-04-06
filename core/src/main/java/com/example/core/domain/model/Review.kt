package com.example.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Review(
    val author: String,
    val name: String,
    val username: String,
    val avatarPath: String,
    val rating: Int,
    val content: String,
    val createdAt: String,
    val id: String,
    val updatedAt: String,
    val url: String
) : Parcelable