package com.example.domain.usecase

import com.example.domain.entity.ImageUrlBO
import io.reactivex.Single

interface GetBaseUrlUseCase {

    fun getBaseUrl(apiKey: String): Single<ImageUrlBO>
}