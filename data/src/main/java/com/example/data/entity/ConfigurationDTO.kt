package com.example.data.entity

import com.google.gson.annotations.SerializedName

data class ConfigurationDTO(
    @SerializedName("images")
    val images: ImageUrlDTO
)