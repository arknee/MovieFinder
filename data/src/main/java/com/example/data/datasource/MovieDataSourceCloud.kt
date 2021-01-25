package com.example.data.datasource

import com.example.data.entity.MovieDTO
import com.example.data.entity.MoviesResponseDTO
import io.reactivex.Single

interface MovieDataSourceCloud {

    fun getPopularMovies(apiKey: String, page: Int): Single<MoviesResponseDTO>

    fun getMovie(apiKey: String, movieId: Int): Single<MovieDTO>
}