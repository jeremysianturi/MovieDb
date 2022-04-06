package com.example.core.helper.helpermapper

import com.example.core.data.source.local.entity.ReviewEntity
import com.example.core.data.source.remote.response.review.ReviewResponse
import com.example.core.domain.model.Review

object DataMapperReview {

    fun mapResponsetoEntities(input: List<ReviewResponse>): List<ReviewEntity> {
        val reviewList = ArrayList<ReviewEntity>()
        input.map {
            val review = ReviewEntity(
                author = it.author,
                name = it.authorDetails.name ?: "No Name",
                username = it.authorDetails.username ?: "No Username",
                avatarPath = it.authorDetails.avatarPath ?: "",
                rating = it.authorDetails.rating ?: 0,
                content = it.content,
                createdAt = it.createdAt,
                id = it.id,
                updatedAt = it.updatedAt,
                url = it.url
            )
            reviewList.add(review)
        }

        return reviewList
    }

    fun mapEntitiestoDomain(input: List<ReviewEntity>): List<Review> =
        input.map {
            Review(
                author = it.author,
                name = it.name,
                username = it.username,
                avatarPath = it.avatarPath,
                rating = it.rating,
                content = it.content,
                createdAt = it.createdAt,
                id = it.id,
                updatedAt = it.updatedAt,
                url = it.url
            )
        }
}