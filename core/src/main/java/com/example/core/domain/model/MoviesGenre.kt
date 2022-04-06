package com.example.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MoviesGenre(
    val id: Int,
    val name: String,
) : Parcelable