package com.example.domain.entity

data class ImageUrlBO(
    val baseUrl: String,
    val secureBaseUrl: String,
    val posterSizes: List<String>
)