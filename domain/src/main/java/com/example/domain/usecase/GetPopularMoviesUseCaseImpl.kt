package com.example.domain.usecase

import com.example.data.repository.MovieRepository
import com.example.domain.entity.MoviesResponseBO
import com.example.domain.mapper.MovieMapper
import com.example.domain.mapper.MovieResponseMapper
import io.reactivex.Single

class GetPopularMoviesUseCaseImpl(
    private val movieRepository: MovieRepository,
    private val movieResponseMapper: MovieResponseMapper,
    private val movieMapper: MovieMapper
) : GetPopularMoviesUseCase {

    override fun getPopularMovies(apiKey: String, page: Int): Single<MoviesResponseBO> {
        return movieRepository.getPopularMovies(apiKey, page)
            .map(movieResponseMapper::map)
            .flatMap { moviesResponse ->
                movieRepository.getFavorites()
                    .map {
                        it.map(movieMapper::map)
                    }.flatMapSingle { favorites ->
                        moviesResponse.results.map { movie ->
                            favorites.find {
                                movie.id == it.id
                            }?.let {
                                movie.isFavorite = true
                            }
                        }
                        Single.just(moviesResponse)
                    }
            }
    }
}