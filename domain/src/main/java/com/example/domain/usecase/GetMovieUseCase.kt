package com.example.domain.usecase

import com.example.domain.entity.MovieBO
import io.reactivex.Single

interface GetMovieUseCase {

    fun getMovie(apiKey: String, movieId: Int): Single<MovieBO>
}