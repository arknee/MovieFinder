package com.example.moviefinder.entity

data class MovieVO(
    val id: Int,
    val posterPath: String,
    val originalTitle: String,
    val overview: String,
    val originalLanguage: String,
    var isFavorite: Boolean
)