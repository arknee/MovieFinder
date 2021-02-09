package com.example.data.entity

import com.google.gson.annotations.SerializedName

data class ImageUrlDTO(
    @SerializedName("base_url")
    val baseUrl: String,
    @SerializedName("secure_base_url")
    val secureBaseUrl: String,
    @SerializedName("poster_sizes")
    val posterSizes: List<String>
)