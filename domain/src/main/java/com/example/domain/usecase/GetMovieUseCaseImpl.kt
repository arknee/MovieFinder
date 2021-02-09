package com.example.domain.usecase

import com.example.data.repository.MovieRepository
import com.example.domain.entity.MovieBO
import com.example.domain.mapper.MovieMapper
import io.reactivex.Single

class GetMovieUseCaseImpl(
    private val movieRepository: MovieRepository,
    private val movieMapper: MovieMapper,
    private val getBaseUrlUseCase: GetBaseUrlUseCase
) : GetMovieUseCase {

    override fun getMovie(apiKey: String, imageSize: Int, movieId: Int): Single<MovieBO> {
        return movieRepository.getMovie(apiKey, movieId)
            .map(movieMapper::map)
            .flatMap { movie ->
                getBaseUrlUseCase.getBaseUrl(apiKey).flatMap { imageUrl ->
                    movieRepository.getFavorites().map {
                        it.map(movieMapper::map)
                    }.flatMap { favorites ->
                        movie.posterPath =
                            "${imageUrl.secureBaseUrl}${imageUrl.posterSizes[imageSize]}${movie.posterPath}"
                        favorites.find {
                            movie.id == it.id
                        }?.let {
                            movie.apply {
                                isFavorite = true
                            }
                        }
                        Single.just(movie)
                    }
                }
            }
    }
}