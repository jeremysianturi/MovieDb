package com.example.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "review")
data class ReviewEntity(

    @ColumnInfo(name = "author")
    val author: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "username")
    val username: String,

    @ColumnInfo(name = "avatar_path")
    val avatarPath: String,

    @ColumnInfo(name = "rating")
    val rating: Int,

    @ColumnInfo(name = "content")
    val content: String,

    @ColumnInfo(name = "created_at")
    val createdAt: String,

    @PrimaryKey
    @NotNull
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "updated_at")
    val updatedAt: String,

    @ColumnInfo(name = "url")
    val url: String,



    )