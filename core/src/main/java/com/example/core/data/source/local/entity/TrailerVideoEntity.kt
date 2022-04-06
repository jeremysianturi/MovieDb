package com.example.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "trailer_video")
data class TrailerVideoEntity(

    @ColumnInfo(name = "iso_639_1")
    val iso_639_1: String,

    @ColumnInfo(name = "iso_3166_1")
    val iso_3166_1: String,

    @ColumnInfo(name = "name")
    val name: String,

    @PrimaryKey
    @NotNull
    @ColumnInfo(name = "key")
    val key: String,

    @ColumnInfo(name = "site")
    val site: String,

    @ColumnInfo(name = "size")
    val size: Int,

    @ColumnInfo(name = "type")
    val type: String,

    @ColumnInfo(name = "official")
    val official: Boolean,

    @ColumnInfo(name = "published_at")
    val publishedAt: String,

    @ColumnInfo(name = "id")
    val id: String

    )