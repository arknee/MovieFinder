package com.example.data.datasource

import com.example.data.api.MovieApi
import com.example.data.entity.ConfigurationDTO
import com.example.data.entity.MovieDTO
import com.example.data.entity.MoviesResponseDTO
import io.reactivex.Single

class MovieDataSourceCloudImpl(private val api: MovieApi): MovieDataSourceCloud {

    override fun getConfiguration(apiKey: String): Single<ConfigurationDTO> {
        return api.getConfiguration(apiKey)
    }

    override fun getPopularMovies(apiKey: String, page: Int): Single<MoviesResponseDTO> {
        return api.getPopularMovies(apiKey, page)
    }

    override fun getMovie(apiKey: String, movieId: Int): Single<MovieDTO> {
        return api.getMovie(movieId, apiKey)
    }
}