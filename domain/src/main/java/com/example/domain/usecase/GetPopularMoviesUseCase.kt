package com.example.domain.usecase

import com.example.domain.entity.MoviesResponseBO
import io.reactivex.Single

interface GetPopularMoviesUseCase {

    fun getPopularMovies(apiKey: String, page: Int = 1): Single<MoviesResponseBO>
}