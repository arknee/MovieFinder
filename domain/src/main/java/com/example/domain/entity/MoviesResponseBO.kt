package com.example.domain.entity

data class MoviesResponseBO(
    val page: Int,
    val totalPages: Int,
    val totalResults: Int,
    val results: List<MovieBO>
)