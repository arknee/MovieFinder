package com.example.moviefinder.entity

data class MoviesPaginationVO(
    var page: Int,
    val totalPages: Int,
    val totalResults: Int
)