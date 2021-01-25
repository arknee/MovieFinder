package com.example.domain.usecase

import com.example.data.repository.MovieRepository
import com.example.domain.entity.MovieBO
import com.example.domain.mapper.MovieMapper
import io.reactivex.Single

class GetMovieUseCaseImpl(
    private val movieRepository: MovieRepository,
    private val movieMapper: MovieMapper
) : GetMovieUseCase {

    override fun getMovie(apiKey: String, movieId: Int): Single<MovieBO> {
        return movieRepository.getMovie(apiKey, movieId)
            .map(movieMapper::map)
            .flatMap { movie ->
                movieRepository.getFavorites()
                    .map {
                        it.map(movieMapper::map)
                    }.flatMapSingle { favorites ->
                        favorites.find {
                            movie.id == it.id
                        }?.let {
                            Single.just(it.apply {
                                isFavorite = true
                            })
                        } ?: Single.just(movie)
                    }
            }
    }
}