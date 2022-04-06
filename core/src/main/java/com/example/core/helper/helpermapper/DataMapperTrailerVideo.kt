package com.example.core.helper.helpermapper

import com.example.core.data.source.local.entity.BannerEntity
import com.example.core.data.source.local.entity.TrailerVideoEntity
import com.example.core.data.source.remote.response.banner.BannerResponse
import com.example.core.data.source.remote.response.trailervideo.TrailerVideoResponse
import com.example.core.domain.model.Banner
import com.example.core.domain.model.TrailerVideo

object DataMapperTrailerVideo {

    fun mapResponsetoEntities(input: List<TrailerVideoResponse>): List<TrailerVideoEntity> {
        val trailerVideoList = ArrayList<TrailerVideoEntity>()
        input.map {
            val trailerVideo = TrailerVideoEntity(
                iso_639_1 = it.iso_639_1,
                iso_3166_1 = it.iso_3166_1,
                name = it.name,
                key = it.key,
                site = it.site,
                size = it.size,
                type = it.type,
                official = it.official,
                publishedAt = it.publishedAt,
                id = it.id
            )
            trailerVideoList.add(trailerVideo)
        }

        return trailerVideoList
    }

    fun mapEntitiestoDomain(input: List<TrailerVideoEntity>): List<TrailerVideo> =
        input.map {
            TrailerVideo(
                iso_639_1 = it.iso_639_1,
                iso_3166_1 = it.iso_3166_1,
                name = it.name,
                key = it.key,
                site = it.site,
                size = it.size,
                type = it.type,
                official = it.official,
                publishedAt = it.publishedAt,
                id = it.id
            )
        }
}