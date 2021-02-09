package com.example.domain.mapper

import com.example.data.entity.ConfigurationDTO
import com.example.domain.entity.ImageUrlBO

interface ConfigurationMapper {

    fun map(input: ConfigurationDTO): ImageUrlBO
}