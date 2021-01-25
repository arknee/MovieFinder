package com.example.moviefinder.entity

data class MoviesResponseVO(
    var page: Int,
    val totalPages: Int,
    val totalResults: Int,
    val results: List<MovieVO>
)