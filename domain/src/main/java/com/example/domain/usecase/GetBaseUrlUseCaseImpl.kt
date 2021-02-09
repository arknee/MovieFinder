package com.example.domain.usecase

import com.example.data.repository.MovieRepository
import com.example.domain.entity.ImageUrlBO
import com.example.domain.mapper.ConfigurationMapper
import io.reactivex.Single

class GetBaseUrlUseCaseImpl(
    private val movieRepository: MovieRepository,
    private val configurationMapper: ConfigurationMapper
) : GetBaseUrlUseCase {

    override fun getBaseUrl(apiKey: String): Single<ImageUrlBO> {
        return movieRepository.getConfiguration(apiKey)
            .map(configurationMapper::map)
    }
}