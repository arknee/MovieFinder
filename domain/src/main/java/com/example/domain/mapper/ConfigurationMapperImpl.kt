package com.example.domain.mapper

import com.example.data.entity.ConfigurationDTO
import com.example.domain.entity.ImageUrlBO

class ConfigurationMapperImpl: ConfigurationMapper {

    override fun map(input: ConfigurationDTO): ImageUrlBO {
        input.images.run {
            return ImageUrlBO(
                baseUrl,
                secureBaseUrl,
                posterSizes
            )
        }
    }
}