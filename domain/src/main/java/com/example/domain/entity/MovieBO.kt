package com.example.domain.entity

data class MovieBO(
    val id: Int,
    var posterPath: String?,
    val originalTitle: String,
    val overview: String,
    val originalLanguage: String,
    var isFavorite: Boolean = false
)